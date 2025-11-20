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

@WebServlet("/updateGoal")
public class UpdateGoalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finance_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "6892@Pass";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read form data
        int goalId = Integer.parseInt(request.getParameter("goalId"));
        String targetName = request.getParameter("targetName");
        BigDecimal savedAmount = new BigDecimal(request.getParameter("savedAmount"));
        BigDecimal targetAmount = new BigDecimal(request.getParameter("targetAmount"));

        // JDBC variables
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL Update query
            String sql = "UPDATE goals SET target_name=?, saved_amount=?, target_amount=? WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, targetName);
            pstmt.setBigDecimal(2, savedAmount);
            pstmt.setBigDecimal(3, targetAmount);
            pstmt.setInt(4, goalId);

            // Execute update
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Success: goal updated
                response.sendRedirect("updateGoal.jsp?success=true");
            } else {
                // Error: goal not updated
                response.sendRedirect("updateGoal.jsp?error=true");
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
