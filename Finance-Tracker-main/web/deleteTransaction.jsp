<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Transaction</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f8ff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
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
            padding: 30px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        .form-container h2 {
            margin-bottom: 20px;
            text-align: center;
        }
        .btn-primary, .btn-secondary {
            margin-top: 20px;
        }
        .alert {
            display: none;
        }
        .alert.show {
            display: block;
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
            <a href="goals.jsp" class="btn btn-block">Goals</a>
            <a href="dashboard.jsp" class="btn btn-block mt-auto">Back to Dashboard</a>
        </div>
        <div class="form-container">
            <h2 class="text-center mb-4">Delete Transaction</h2>
            
            <!-- Success Message -->
            <div class="alert alert-success <% if ("Transaction deleted successfully!".equals(request.getAttribute("successMessage"))) { %> show <% } %>">
                <strong>Success!</strong> <%= request.getAttribute("successMessage") %>
            </div>
            
            <!-- Error Message -->
            <div class="alert alert-danger <% if ("Failed to delete transaction.".equals(request.getAttribute("errorMessage"))) { %> show <% } %>">
                <strong>Error!</strong> <%= request.getAttribute("errorMessage") %>
            </div>
            
            <form action="deleteTransaction" method="post">
                <div class="form-group">
                    <label for="id">Transaction ID</label>
                    <input type="text" id="id" name="id" class="form-control" placeholder="Enter transaction ID" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Delete Transaction</button>
                
            </form>
        </div>
    </div>
</body>
</html>
