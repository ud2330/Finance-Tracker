<%-- 
    Document   : goals
    Created on : 7 Jul, 2024, 3:57:29 PM
    Author     : Lara Dharshini P
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Goals Management</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f8ff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .goals-container {
            background: white;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
            padding: 40px;
            max-width: 60%; /* Adjust to fit all buttons within the view */
            width: 100%;
            text-align: center;
            margin: 20px;
        }
        .goals-container h1 {
            font-size: 2.5em;
            color: #333;
            margin-bottom: 30px;
            border-bottom: 2px solid #ddd;
            padding-bottom: 10px;
            font-family: Arial, sans-serif;
        }
        .btn-container {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-wrap: wrap;
            gap: 30px; /* Space between buttons */
        }
        .btn {
            width: 220px;
            height: 100px;
            padding: 20px;
            text-align: center;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s, box-shadow 0.3s;
            text-decoration: none;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 16px;
            font-weight: bold;
            box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
        }
        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }
        .btn-success {
            background-color: #28a745;
        }
        .btn-warning {
            background-color: darkorange;
        }
        .btn-danger {
            background-color: #dc3545;
        }
        .btn-info {
            background-color: #17a2b8;
        }
        
    </style>
</head>
<body>
    <div class="goals-container">
        <h1>Financial Goals</h1>
        <div class="btn-container">
            <a href="addGoal.jsp" class="btn btn-success">Add Goal</a>
            <a href="updateGoal.jsp" class="btn btn-warning">Update Goal</a>
            <a href="deleteGoal.jsp" class="btn btn-danger">Delete Goal</a>
            <a href="viewGoals.jsp" class="btn btn-info">View Goals</a>
        </div>
    </div>
</body>
</html>

