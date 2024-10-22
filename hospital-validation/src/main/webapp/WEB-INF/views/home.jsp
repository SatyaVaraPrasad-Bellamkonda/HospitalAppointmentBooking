<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Our Hospital Appointment Booking System</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            background-color: #e9ecef;
            color: #333;
        }

        header {
            background: #007BFF;
            color: #fff;
            padding: 20px 0;
            text-align: center;
        }

        nav ul {
            list-style: none;
            padding: 0;
        }

        nav ul li {
            display: inline;
            margin: 0 15px;
        }

        nav ul li a {
            color: #fff;
            text-decoration: none;
            font-weight: bold;
        }

        .welcome-section {
            padding: 50px 20px;
            text-align: center;
            background: #f4f4f4;
            border-radius: 8px;
            margin: 20px auto;
            max-width: 800px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .welcome-section h1 {
            font-size: 2.5em;
            margin-bottom: 20px;
            color: #007BFF;
        }

        .welcome-section p {
            font-size: 1.2em;
            margin-bottom: 30px;
        }

        .btn {
            background: #FF6F61;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
            transition: background 0.3s;
        }

        .btn:hover {
            background: #FF4F3D;
        }

        footer {
            background: #333;
            color: #fff;
            text-align: center;
            padding: 10px 0;
            position: relative;
            bottom: 0;
            width: 100%;
            margin-top: 20px;
        }
    </style>
</head>
<body>
        
        <div>
	<%@ include file="/WEB-INF/views/index.jsp" %>
</div>

    <div class="welcome-section">
        <h1>Welcome ${name }!</h1>
        <p>We are dedicated to providing you with the best healthcare services. Our user-friendly system allows you to easily book appointments, access medical services, and stay connected with your healthcare providers.</p>
        <p>Explore our services and take control of your health today!</p>
        <!-- <a href="#book" class="btn">Get Started</a> -->
    </div>

   
</body>
</html>
