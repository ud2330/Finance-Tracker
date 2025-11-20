<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Finance Dashboard</title>
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
        .dashboard-container {
            background: white;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
            padding: 40px;
            max-width: 80%; /* Adjust to fit all buttons within the view */
            width: 100%;
            text-align: center;
            margin: 20px;
        }
        .dashboard-container h1 {
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
        /* Override text color on hover for specific buttons */
        .btn.btn-custom-profile:hover,
        .btn.btn-goals:hover{
            color: #ffffff !important; /* Keep text color white on hover */
        }
        .btn-primary {
            background-color: #007bff;
        }
        .btn-success {
            background-color: #28a745;
        }
        .btn-warning {
            background-color: golden;
        }
        .btn-danger {
            background-color: #dc3545;
        }
        .btn-info {
            background-color: #17a2b8;
        }
        .btn-secondary {
            background-color: #6c757d;
        }
        .btn-custom-profile {
            background-color: #6f42c1; /* Custom purple color */
        }
        .btn-custom-profile:hover {
            background-color: #5a32a1; /* Darker purple on hover */
        }
        .btn-goals {
            background-color: royalblue; /* Custom yellow color */
        }
        .btn-goals:hover {
            background-color: royalblue; /* Darker yellow on hover */
        }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <h1>Finance Dashboard</h1>
        <div class="btn-container">
            <a href="viewTransactions.jsp" class="btn btn-primary">View Transactions</a>
            <a href="addTransaction.jsp" class="btn btn-success">Add Transaction</a>
            <a href="updateTransaction.jsp" class="btn btn-warning">Update Transaction</a>
            <a href="deleteTransaction.jsp" class="btn btn-danger">Delete Transaction</a>
            <a href="monthlyReport.jsp" class="btn btn-info">Monthly Report</a>
            <a href="setBudget.jsp" class="btn btn-secondary">Set Budget</a>
            <a href="editProfile.jsp" class="btn btn-custom-profile">Edit Profile</a>
            <a href="goals.jsp" class="btn btn-goals">Goals</a>
        </div>
    </div>
</body>
</html>
