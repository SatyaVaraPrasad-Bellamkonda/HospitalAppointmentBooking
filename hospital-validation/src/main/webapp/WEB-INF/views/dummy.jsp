<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: white;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            border: 1px solid #007BFF;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
        }
        h1 {
            color: #007BFF;
            text-align: center;
        }
        .field {
            margin: 15px 0;
        }
        .label {
            font-weight: bold;
            color: #007BFF;
        }
        .input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
        button {
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #0056b3;
        }
        footer {
            text-align: center;
            margin-top: 20px;
            color: #007BFF;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Signup</h1>
    <form action="doctorDetailsSubmit" method="POST"> <!-- Change to your actual URL -->
       
        <div class="field">
            <span class="label">Name:</span>
            <input type="text" name="name" class="input" placeholder="Enter your name" required>
        </div>
        <div class="field">
            <span class="label">Degree:</span>
            <input type="text" name="degree" class="input" placeholder="Enter your degree" required>
        </div>
        <div class="field">
            <span class="label">Experience (years):</span>
            <input type="number" name="experience" class="input" placeholder="Enter years of experience" required>
        </div>
        <div class="field">
            <span class="label">Specialist:</span>
            <input type="text" name="specialist" class="input" placeholder="Enter your specialty" required>
        </div>
        <div class="field">
            <span class="label">Mobile:</span>
            <input type="text" name="mobile" class="input" placeholder="Enter your mobile number" required>
        </div>
        <div class="field">
            <span class="label">Email:</span>
            <input type="email" name="mail" class="input" placeholder="Enter your email" required>
        </div>
        <div class="field">
            <span class="label">Password:</span>
            <input type="password" name="password" class="input" placeholder="Create a password" required>
        </div>
        
        <div class="button-container">
            <button type="submit">Signup</button>
        </div>
    </form>
</div>

<footer>
    &copy; 2024 Health Management System
</footer>

</body>
</html>
