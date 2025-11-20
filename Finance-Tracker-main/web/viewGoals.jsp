<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Goals</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center; /* Center content horizontally */
            align-items: center;
            height: 100vh;
            background-color: #f0f8ff;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            max-width: 1200px;
            background: #ffffff;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            overflow: hidden;
            padding: 40px;
        }
        .container h2 {
            margin-bottom: 20px;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .btn {
            margin-top: 10px;
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
    <div class="container">
        <h2>View Goals</h2>
        <form action="viewGoals" method="get">
            <div class="form-group">
                <label for="id">Goal ID (leave blank to view all):</label>
                <input type="text" id="id" name="id" class="form-control" placeholder="Enter Goal ID">
            </div>
            <button type="submit" class="btn btn-primary btn-block">View Goals</button>
            
        </form>
    </div>
</body>
</html>
