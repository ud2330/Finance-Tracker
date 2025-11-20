package com.finance.servlet;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateTransaction")
public class UpdateTransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finance_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "6892@Pass";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read form data
        String idStr = request.getParameter("id");
        String date = request.getParameter("date");
        String amountStr = request.getParameter("amount");
        String category = request.getParameter("category");
        String description = request.getParameter("description");

        int id = Integer.parseInt(idStr);
        BigDecimal amount = new BigDecimal(amountStr);

        // JDBC variables
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL Update query
            String sql = "UPDATE transactions SET date = ?, amount = ?, category = ?, description = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, java.sql.Date.valueOf(date)); // Convert string to SQL date
            pstmt.setBigDecimal(2, amount);
            pstmt.setString(3, category);
            pstmt.setString(4, description);
            pstmt.setInt(5, id);

            // Execute update
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Redirect to updateTransaction.jsp with success message
                response.sendRedirect("updateTransaction.jsp?success=Transaction+updated+successfully");
            } else {
                throw new ServletException("Failed to update transaction.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Database connection error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
