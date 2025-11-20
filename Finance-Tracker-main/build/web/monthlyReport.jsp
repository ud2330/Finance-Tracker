<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Monthly Transaction Report</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f8ff;
        }
        .container-fluid {
            display: flex;
            height: 95vh;
            width: 90%;
            max-width: 1200px;
            background: #ffffff;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            overflow: hidden;
        }
        .sidebar {
            flex: 0 0 250px;
            background: #007bff; /* Blue */
            color: white;
            padding: 20px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            border-radius: 15px 0 0 15px;
        }
        .sidebar h2 {
            margin-bottom: 30px;
            font-size: 1.5em;
        }
        .sidebar a {
            color: white;
            text-decoration: none;
            margin: 10px 0;
            padding: 10px;
            border-radius: 5px;
            background: rgba(255, 255, 255, 0.1);
            transition: background 0.3s ease;
        }
        .sidebar a:hover {
            background: rgba(255, 255, 255, 0.2);
        }
        .form-container {
            flex: 1;
            padding: 40px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        .form-container h2 {
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <div class="sidebar">
            <h2>Finance Tracker</h2>
            <a href="viewTransactions.jsp" class="btn btn-block">View Transactions</a>
            <a href="addTransaction.jsp" class="btn btn-block">Add Transaction</a>
            <a href="updateTransaction.jsp" class="btn btn-block">Update Transaction</a>
            <a href="deleteTransaction.jsp" class="btn btn-block">Delete Transaction</a>
            <a href="monthlyReport.jsp" class="btn btn-block">Monthly Report</a>
            <a href="setBudget.jsp" class="btn btn-block">Set Budget</a>
            <a href="editProfile.jsp" class="btn btn-block">Edit Profile</a>
            <a href="goals.jsp" class="btn btn-block">Goals</a> <!-- Added Goals button -->
            <a href="dashboard.jsp" class="btn btn-block mt-auto">Back to Dashboard</a>
        </div>
        <div class="form-container animation">
            <h2 class="text-center">Monthly Transaction Report</h2>
            <form action="MonthlyreportServlet" method="get">
                <button type="submit" class="btn btn-primary btn-block">Generate Report</button>
            </form>
       </div>
    </div>
</body>
</html>
