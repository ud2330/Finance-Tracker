package com.finance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/monthlyReport")
public class MonthlyreportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finance_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "6892@Pass";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // JDBC variables
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL query to aggregate transactions by month and category, and join with budgets table
            String sql = "SELECT DATE_FORMAT(MAX(t.date), '%b - %Y') AS month, "
                       + "SUM(CASE WHEN t.category = 'income' THEN t.amount ELSE 0 END) AS income, "
                       + "SUM(CASE WHEN t.category = 'expense' THEN t.amount ELSE 0 END) AS expenses, "
                       + "b.monthly_budget "
                       + "FROM transactions t "
                       + "LEFT JOIN budgets b ON YEAR(t.date) = b.year AND MONTH(t.date) = b.month "
                       + "GROUP BY YEAR(t.date), MONTH(t.date), b.monthly_budget";

            pstmt = conn.prepareStatement(sql);

            // Execute query
            rs = pstmt.executeQuery();

            // Use a Map to store the data for each month
            Map<String, BudgetData> monthlyData = new HashMap<>();

            // JSON arrays for chart data
            StringBuilder monthsJson = new StringBuilder("[");
            StringBuilder incomeJson = new StringBuilder("[");
            StringBuilder expensesJson = new StringBuilder("[");

            // Process the result set
            while (rs.next()) {
                String month = rs.getString("month");
                double income = rs.getDouble("income");
                double expenses = rs.getDouble("expenses");
                double budget = rs.getDouble("monthly_budget");
                double balance = income - expenses;

                String budgetStatus;
                if (expenses > budget) {
                    budgetStatus = "<span style='color:red;'>Exceeded</span>";
                } else if (expenses < budget) {
                    budgetStatus = "<span style='color:green;'>Within Budget</span>";
                } else {
                    budgetStatus = "<span style='color:gray;'>Null</span>";
                }

                monthlyData.put(month, new BudgetData(income, expenses, balance, budget, budgetStatus));

                // Append to JSON arrays
                monthsJson.append("\"").append(month).append("\",");
                incomeJson.append(income).append(",");
                expensesJson.append(expenses).append(",");
            }

            // Remove trailing commas and close JSON arrays
            if (monthsJson.length() > 1) {
                monthsJson.setLength(monthsJson.length() - 1);
            }
            monthsJson.append("]");

            if (incomeJson.length() > 1) {
                incomeJson.setLength(incomeJson.length() - 1);
            }
            incomeJson.append("]");

            if (expensesJson.length() > 1) {
                expensesJson.setLength(expensesJson.length() - 1);
            }
            expensesJson.append("]");

            // Generate HTML response
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("    <title>Monthly Transaction Report</title>");
            out.println("    <style>");
            out.println("        body {");
            out.println("            font-family: Arial, sans-serif;");
            out.println("            background-color: #f0f8ff;");
            out.println("            padding: 20px;");
            out.println("        }");
            out.println("        h2 {");
            out.println("            text-align: center;");
            out.println("            margin-bottom: 20px;");
            out.println("        }");
            out.println("        table {");
            out.println("            width: 100%;");
            out.println("            border-collapse: collapse;");
            out.println("            margin-top: 20px;");
            out.println("        }");
            out.println("        th, td {");
            out.println("            padding: 12px;");
            out.println("            text-align: center;");
            out.println("            border: 1px solid #ddd;");
            out.println("        }");
            out.println("        th {");
            out.println("            background-color: #007bff;");
            out.println("            color: white;");
            out.println("        }");
            out.println("        tr:nth-child(even) {");
            out.println("            background-color: #f9f9f9;");
            out.println("        }");
            out.println("        canvas {");
            out.println("            margin-top: 30px;");
            out.println("            width: 600px;");
            out.println("            height: 400px;");
            out.println("            margin-left: auto;");
            out.println("            margin-right: auto;");
            out.println("        }");
            out.println("    </style>");
            // Add Chart.js from reliable CDN
            out.println("    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <h2>Monthly Transaction Report</h2>");
            out.println("    <table>");
            out.println("        <tr>");
            out.println("            <th>Month</th>");
            out.println("            <th>Income</th>");
            out.println("            <th>Expenses</th>");
            out.println("            <th>Balance</th>");
            out.println("            <th>Budget</th>");
            out.println("            <th>Budget Status</th>");
            out.println("        </tr>");

            // Iterate through the monthly data and print
            for (Map.Entry<String, BudgetData> entry : monthlyData.entrySet()) {
                String month = entry.getKey();
                BudgetData data = entry.getValue();

                out.println("        <tr>");
                out.println("            <td>" + month + "</td>");
                out.println("            <td>Rs. " + String.format("%.2f", data.getIncome()) + "</td>");
                out.println("            <td>Rs. " + String.format("%.2f", data.getExpenses()) + "</td>");
                out.println("            <td>Rs. " + String.format("%.2f", data.getBalance()) + "</td>");
                out.println("            <td>Rs. " + String.format("%.2f", data.getBudget()) + "</td>");
                out.println("            <td>" + data.getBudgetStatus() + "</td>");
                out.println("        </tr>");
            }

            out.println("    </table>");

            // Add canvas for chart
            out.println("    <canvas id=\"monthlyChart\" width=\"600\" height=\"400\"></canvas>");

            // Add Chart.js script
            out.println("    <script>");
            out.println("        console.log('Chart Data:', {");
            out.println("            labels: " + monthsJson.toString() + ",");
            out.println("            income: " + incomeJson.toString() + ",");
            out.println("            expenses: " + expensesJson.toString());
            out.println("        });");
            out.println("        const ctx = document.getElementById('monthlyChart').getContext('2d');");
            out.println("        const monthlyChart = new Chart(ctx, {");
            out.println("            type: 'line',");
            out.println("            data: {");
            out.println("                labels: " + monthsJson.toString() + ",");
            out.println("                datasets: [{");
            out.println("                    label: 'Income',");
            out.println("                    data: " + incomeJson.toString() + ",");
            out.println("                    borderColor: 'green',");
            out.println("                    backgroundColor: 'rgba(0, 255, 0, 0.2)',");
            out.println("                    fill: true");
            out.println("                }, {");
            out.println("                    label: 'Expenses',");
            out.println("                    data: " + expensesJson.toString() + ",");
            out.println("                    borderColor: 'red',");
            out.println("                    backgroundColor: 'rgba(255, 0, 0, 0.2)',");
            out.println("                    fill: true");
            out.println("                }]");
            out.println("            },");
            out.println("            options: {");
            out.println("                responsive: true,");
            out.println("                plugins: {");
            out.println("                    legend: {");
            out.println("                        display: true,");
            out.println("                        position: 'top',");
            out.println("                    },");
            out.println("                    tooltip: {");
            out.println("                        mode: 'index',");
            out.println("                        intersect: false,");
            out.println("                    }");
            out.println("                },");
            out.println("                scales: {");
            out.println("                    x: {");
            out.println("                        display: true,");
            out.println("                        title: {");
            out.println("                            display: true,");
            out.println("                            text: 'Month'");
            out.println("                        }");
            out.println("                    },");
            out.println("                    y: {");
            out.println("                        display: true,");
            out.println("                        title: {");
            out.println("                            display: true,");
            out.println("                            text: 'Amount (Rs.)'");
            out.println("                        }");
            out.println("                    }");
            out.println("                }");
            out.println("            }");
            out.println("        });");
            out.println("    </script>");

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Inner class to store budget data
    class BudgetData {
        private double income;
        private double expenses;
        private double balance;
        private double budget;
        private String budgetStatus;

        public BudgetData(double income, double expenses, double balance, double budget, String budgetStatus) {
            this.income = income;
            this.expenses = expenses;
            this.balance = balance;
            this.budget = budget;
            this.budgetStatus = budgetStatus;
        }

        public double getIncome() {
            return income;
        }

        public double getExpenses() {
            return expenses;
        }

        public double getBalance() {
            return balance;
        }

        public double getBudget() {
            return budget;
        }

        public String getBudgetStatus() {
            return budgetStatus;
        }
    }
}
