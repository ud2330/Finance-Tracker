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

@WebServlet("/profile")
public class ProfilesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finance_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "6892@Pass";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h2>This servlet only handles POST requests for user profile submission.</h2>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Prepare the SQL statement
            String sql = "INSERT INTO users (username, password, email, gender, phone) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, gender);
            stmt.setString(5, phone);
            // Execute the statement
            int rows = stmt.executeUpdate();
            // Redirect to the dashboard
            response.sendRedirect("welcome.jsp");
        } catch (ClassNotFoundException e) {
            log("JDBC Driver not found", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "JDBC Driver not found.");
        } catch (SQLException e) {
            log("SQL error", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "SQL error occurred.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                log("Error closing resources", e);
            }
        }
    }
}
