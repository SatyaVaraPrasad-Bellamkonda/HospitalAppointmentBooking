<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Access Denied</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            text-align: center;
            padding: 20px;
            border: 2px solid #e74c3c;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            font-size: 24px;
            color: #e74c3c;
        }
        p {
            font-size: 18px;
            margin: 10px 0;
        }
        a {
            text-decoration: none;
            color: #3498db;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style> 
</head>
<body>
<div>
	<%@ include file="/WEB-INF/views/index.jsp" %>
</div>

    <div class="container"> 
        <h1>Access Denied / Page Under Contruction</h1>
        <p>Hey ${name} , you do not have any permissions to access this page.</p>
        <p><a href="index">Return to Home</a></p>
    </div>
</body>
</html>
