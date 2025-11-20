<%-- 
    Document   : welcome
    Created on : 17 Jun, 2024, 9:44:18 AM
    Author     : Lara Dharshini P
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f8ff; /* Light cyan background */
        }
        .welcome-container {
            text-align: center;
            padding: 40px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
        }
        .welcome-message {
            font-size: 24px;
            font-weight: bold;
            color: #333;
        }
        .catchy-line {
            font-size: 20px;
            margin-top: 10px;
            color: #555;
        }
        .btn-primary {
            margin-top: 20px;
        }
        .btn-secondary {
            margin-top: 20px;
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
    <div class="welcome-container animation">
        <h1 class="welcome-message">Welcome to Finance Tracker!</h1>
        <h6 style="color:green;">Your registration was successful!</h6>
        <p class="catchy-line">Your journey towards financial excellence begins here.</p>
        <p class="catchy-line">“Track expenses, manage budgets, and plan investments seamlessly.”</p>
        <a href="dashboard.jsp" class="btn btn-primary">Go to Dashboard</a>
        <a href="profile.jsp" class="btn btn-secondary">Go back to Profile</a>
    </div>
</body>
</html>


