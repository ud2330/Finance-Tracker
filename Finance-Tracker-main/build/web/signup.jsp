<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
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
        .signup-form {
            width: 350px;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
            
        }
        .signup-form h3 {
            margin-bottom: 20px;
        }
        .btn-primary {
            margin-top: 10px;
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
    <div class="signup-form animation">
        <h3 class="text-center">Sign Up</h3>
        <!-- Error Message -->
        <div class="alert alert-danger <% if (request.getParameter("error") != null) { %> show <% } %>">
            <strong>Error!</strong> Username already exists. Please choose another.
        </div>
        <form action="signup" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Sign Up</button>
        </form>
        <div class="login-link">
            <p>Already have an account? <a href="login.jsp" style="color: #ffc107;">Log in here</a></p>
        </div>
    </div>
</body>
</html>
