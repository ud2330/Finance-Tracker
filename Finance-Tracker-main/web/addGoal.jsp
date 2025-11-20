<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Goal</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f8ff;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            margin: 0 0 20px;
            padding-bottom: 10px;
            border-bottom: 2px solid #e0e0e0;
            color: #333;
            font-size: 24px;
            text-align: center;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            color: #333;
        }
        input[type="text"], input[type="number"], select {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }
        input[type="text"]:focus, input[type="number"]:focus, select:focus {
            border-color: #007bff;
            outline: none;
        }
        .btn {
            display: block;
            width: 100%;
            padding: 10px;
            border: none;
            background-color: #007bff;
            color: #fff;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .alert {
            margin-top: 20px;
            padding: 15px;
            border-radius: 4px;
        }
        .alert-success {
            background-color: #d4edda;
            border-color: #c3e6cb;
            color: #155724;
        }
        .alert-danger {
            background-color: #f8d7da;
            border-color: #f5c6cb;
            color: #721c24;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add Goal</h2>
        
        <%-- Success Message --%>
        <% if ("true".equals(request.getParameter("success"))) { %>
            <div class="alert alert-success">
                <strong>Success!</strong> Goal added successfully.
            </div>
        <% } %>
        
        <%-- Error Message --%>
        <% if ("true".equals(request.getParameter("error"))) { %>
            <div class="alert alert-danger">
                <strong>Error!</strong> Failed to add goal.
            </div>
        <% } %>
        
        <form action="addGoal" method="post">
            <div class="form-group">
                <label for="targetName">Target Name:</label>
                <input type="text" id="targetName" name="targetName" required>
            </div>
            <div class="form-group">
                <label for="savedAmount">Saved Amount:</label>
                <input type="number" id="savedAmount" name="savedAmount" step="0.01" required>
            </div>
            <div class="form-group">
                <label for="targetAmount">Target Amount:</label>
                <input type="number" id="targetAmount" name="targetAmount" step="0.01" required>
            </div>
            <button type="submit" class="btn">Add Goal</button>
            
        </form>
    </div>
</body>
</html>
