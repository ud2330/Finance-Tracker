package com.finance.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // In-memory map to store users
    private static Map<String, String> users = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Add the new user to the map
        if (username != null && password != null && !users.containsKey(username)) {
            users.put(username, password);
            // Redirect to login page with success message
            response.sendRedirect("login.jsp?signup=success");
        } else {
            // Redirect back to signup page with error
            response.sendRedirect("signup.jsp?error=exists");
        }
    }

    public static Map<String, String> getUsers() {
        return users;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("signup.jsp");
    }
}
