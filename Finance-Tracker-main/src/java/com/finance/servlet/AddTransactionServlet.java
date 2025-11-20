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

@WebServlet("/addTransaction")
public class AddTransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finance_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "6892@Pass";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read form data
        String date = request.getParameter("date");
        String amount = request.getParameter("amount");
        String category = request.getParameter("category");
        String description = request.getParameter("description");

        // JDBC variables
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL Insert query
            String sql = "INSERT INTO transactions (date, amount, category, description) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, java.sql.Date.valueOf(date));  // Convert string to SQL date
            pstmt.setBigDecimal(2, new BigDecimal(amount));
            pstmt.setString(3, category);
            pstmt.setString(4, description);

            // Execute insert
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                // Success: transaction added
                response.sendRedirect("addTransaction.jsp?success=true");
            } else {
                // Error: transaction not added
                response.sendRedirect("addTransaction.jsp?error=true");
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
