<%-- 
    Document   : login
    Created on : 17 Jun, 2024, 9:43:16 AM
    Author     : Lara Dharshini P
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f8ff;
            background-size: cover;
            
        }
        .login-form {
            width: 350px;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
            
        }
        .login-form h3 {
            margin-bottom: 20px;
        }
        .btn-primary {
            margin-top: 10px;
        }
        .signup-link {
            margin-top: 20px;
        }
        .alert {
            display: none;
        }
        .alert.show {
            display: block;
        }
        .animation {
            animation: fadeIn 1s ease-in-out;
        }
        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
    </style>
</head>
<body>
    <div class="login-form animation">
        <h3 class="text-center">Login</h3>
        <!-- Success Message -->
        <div class="alert alert-success <% if (request.getParameter("signup") != null) { %> show <% } %>">
            <strong>Success!</strong> You can now log in with your new account.
        </div>
        <!-- Error Message -->
        <div class="alert alert-danger <% if (request.getParameter("error") != null) { %> show <% } %>">
            <strong>Error!</strong> Invalid username or password.
        </div>
        <form action="login" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Login</button>
        </form>
        <div class="signup-link">
            <p>Don't have an account? <a href="signup.jsp" style="color: #ffc107;">Sign up here</a></p>
        </div>
    </div>
</body>
</html>
