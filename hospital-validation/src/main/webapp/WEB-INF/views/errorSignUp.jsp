<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff; /* Light blue background */
            color: #003366; /* Dark blue text */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 20px;
        }

        .container {
            text-align: center;
            border: 2px solid #003366; /* Dark blue border */
            border-radius: 10px;
            padding: 40px;
            background-color: #ffffff; /* White background for the container */
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            max-width: 400px; /* Set a maximum width */
            width: 100%; /* Responsive width */
        }

        h1 {
            margin-bottom: 20px;
            font-size: 24px; /* Adjusted font size */
        }

        .error-message {
            font-size: 18px;
            margin-bottom: 30px;
        }

        .back-link {
            text-decoration: none;
            color: #ffffff;
            background-color: #003366; /* Dark blue button */
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s;
            font-size: 16px; /* Adjusted font size */
        }

        .back-link:hover {
            background-color: #00509e; /* Lighter blue on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Signup Error</h1>
        <p class="error-message">Sorry,  ${name}  already exists. Try with another Mail ID.</p>
        <a href="${pageContext.request.contextPath}/loginSign" class="back-link">Go Back to Signup</a>
    </div>
</body>
</html>
