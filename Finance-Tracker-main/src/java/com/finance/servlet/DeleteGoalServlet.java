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

@WebServlet("/deleteGoal")
public class DeleteGoalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finance_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "6892@Pass";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read goal ID to delete
        int goalId = Integer.parseInt(request.getParameter("goalId"));

        // JDBC variables
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL Delete query
            String sql = "DELETE FROM goals WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, goalId);

            // Execute delete
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                // Success: goal deleted
                response.sendRedirect("deleteGoal.jsp?success=true");
            } else {
                // Error: goal not deleted
                response.sendRedirect("deleteGoal.jsp?error=true");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Database connection error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
