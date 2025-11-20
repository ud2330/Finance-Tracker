package com.finance.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/setBudget")
public class BudgetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finance_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "6892@Pass";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String monthlyBudgetStr = request.getParameter("monthly_budget");
        String monthStr = request.getParameter("month");
        String yearStr = request.getParameter("year");

        // Validate inputs
        if (category == null || monthlyBudgetStr == null || monthStr == null || yearStr == null ||
            category.isEmpty() || monthlyBudgetStr.isEmpty() || monthStr.isEmpty() || yearStr.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("setBudget.jsp").forward(request, response);
            return;
        }

        double monthlyBudget;
        int month;
        int year;
        
        try {
            monthlyBudget = Double.parseDouble(monthlyBudgetStr);
            month = Integer.parseInt(monthStr);
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid number format.");
            request.getRequestDispatcher("setBudget.jsp").forward(request, response);
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Prepare the SQL statement
            String sql = "INSERT INTO budgets (category, monthly_budget, month, year) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, category);
            stmt.setDouble(2, monthlyBudget);
            stmt.setInt(3, month);
            stmt.setInt(4, year);

            // Execute the statement
            int rows = stmt.executeUpdate();

            // Check if insert was successful
            if (rows > 0) {
                request.setAttribute("successMessage", "Budget set successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to set budget.");
            }
        } catch (ClassNotFoundException e) {
            log("JDBC Driver not found", e);
            request.setAttribute("errorMessage", "JDBC Driver not found.");
        } catch (SQLException e) {
            log("SQL error", e);
            request.setAttribute("errorMessage", "SQL error occurred: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                log("Error closing resources", e);
            }
        }

        request.getRequestDispatcher("setBudget.jsp").forward(request, response);
    }
}
