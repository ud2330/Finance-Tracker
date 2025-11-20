package com.finance.servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteTransaction")
public class DeleteTransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finance_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "6892@Pass";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read form data
        String id = request.getParameter("id");

        // JDBC variables
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL Delete query
            String sql = "DELETE FROM transactions WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));

            // Execute delete
            int rowsDeleted = pstmt.executeUpdate();

            // Set success or error messages as request attributes
            if (rowsDeleted > 0) {
                request.setAttribute("successMessage", "Transaction deleted successfully!");
                request.removeAttribute("errorMessage"); // Remove any previous error message
            } else {
                request.setAttribute("errorMessage", "Failed to delete transaction.");
                request.removeAttribute("successMessage"); // Remove any previous success message
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database connection error: " + e.getMessage());
            request.removeAttribute("successMessage"); // Remove any previous success message
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Forward to deleteTransaction.jsp for displaying the message
        RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteTransaction.jsp");
        dispatcher.forward(request, response);
    }
}
