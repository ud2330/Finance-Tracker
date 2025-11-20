<%-- 
    Document   : index
    Created on : 7 Jul, 2024, 1:05:10 PM
    Author     : Lara Dharshini P
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Finance Tracker</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f8ff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: 'Arial', sans-serif;
        }
        .container {
            text-align: center;
        }
         h1 {
            font-size: 4em;
            font-weight: bolder;
            margin-bottom: 0.2em;
            color: black;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3);
        }
        h5 {
         
            font-weight: bold;
            margin-bottom: 1em;
            color: brown;
        }
        .btn-get-started {
            font-size: 15px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .btn-get-started:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>FINANCE TRACKER</h1>
        <h5>Plan, Save, and Prosper.</h5>
        <button class="btn-get-started" onclick="location.href='signup.jsp'">Get Started</button>
    </div>
</body>
</html>
