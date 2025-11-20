package com.finance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewGoals")
public class ViewGoalsServlet extends HttpServlet {
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

            // SQL Select query
            String sql = "SELECT * FROM goals";
            pstmt = conn.prepareStatement(sql);

            // Execute query
            rs = pstmt.executeQuery();

            // Set response content type
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            // Start HTML output
            out.println("<html>");
            out.println("<head>");
            out.println("<title>View Goals</title>");
            out.println("<link href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f0f8ff; margin: 0; padding: 0; }");
            out.println(".container { width: 90%; margin: 30px auto; }");
            out.println(".table-container { margin-top: 20px; box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); border-radius: 8px; overflow: hidden; }");
            out.println(".table { width: 100%; border-collapse: collapse; }");
            out.println(".table th, .table td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println(".table th { background-color: #007bff; color: white; }");
            out.println(".table tbody tr:nth-child(even) { background-color: #f9f9f9; }");
            out.println(".table tbody tr:hover { background-color: #f1f1f1; }");
            out.println(".header { text-align: center; margin-top: 20px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h2 class='header'>Financial Goals</h2>");
            out.println("<div class='table-container'>");
            out.println("<table class='table table-striped table-bordered'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Target Name</th>");
            out.println("<th>Saved Amount</th>");
            out.println("<th>Target Amount</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            // Iterate through the result set and print data
            while (rs.next()) {
                int id = rs.getInt("id");
                String targetName = rs.getString("target_name");
                BigDecimal savedAmount = rs.getBigDecimal("saved_amount");
                BigDecimal targetAmount = rs.getBigDecimal("target_amount");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + targetName + "</td>");
                out.println("<td>" + savedAmount + "</td>");
                out.println("<td>" + targetAmount + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Database connection error: " + e.getMessage());
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
}
