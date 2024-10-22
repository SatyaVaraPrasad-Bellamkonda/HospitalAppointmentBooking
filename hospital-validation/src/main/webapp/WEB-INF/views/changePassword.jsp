<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
    <style>
        body {
            background-color: #f0f4ff; /* Light blue background */
            font-family: Arial, sans-serif;
        }

        .form-container {
            width: 300px;
            margin: auto;
            padding: 20px;
            background-color: #ffffff; /* White background for form */
            border: 1px solid #007bff; /* Blue border */
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #007bff; /* Blue text for heading */
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #333; /* Darker text for labels */
        }

        .form-group input[type="text"], input {
            width: 100%;
            padding: 10px;
            border: 1px solid #007bff; /* Blue border for inputs */
            border-radius: 3px;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }

        .form-group input:focus {
            border-color: #0056b3; /* Darker blue on focus */
            outline: none;
        }

        .form-group input.error {
            border-color: red; /* Red border for error state */
        }

        .form-group button {
            width: 100%;
            padding: 10px;
            background-color: #007bff; /* Blue background for button */
            color: white; /* White text for button */
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .form-group button:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }

        #error-message {
            margin-top: 5px;
            color: red; /* Error message color */
        }
    </style>
    <script>
        function validatePassword() {
            var newPassword = document.getElementById("newPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var errorMessage = document.getElementById("error-message");

            if (newPassword !== confirmPassword) {
                errorMessage.textContent = "Password mismatch";
                return false; // Prevent form submission
            } else {
                errorMessage.textContent = ""; // Clear the message
            }
            return true; // Allow form submission
        }

        function checkPasswordMatch() {
            var newPassword = document.getElementById("newPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var confirmInput = document.getElementById("confirmPassword");

            if (newPassword !== confirmPassword) {
                confirmInput.classList.add("error");
            } else {
                confirmInput.classList.remove("error");
            }
        }
    </script>
</head>
<body>
<div>
    <%@ include file="/WEB-INF/views/index.jsp" %>
</div>
<div class="form-container">
    <h2>Change Password</h2><br>
    					<c:if test="${not empty success }">
						<div style="color:green;">
							${success}
							</div>
						</c:if>
						<c:if test="${not empty fail }">
						<div style="color:red;">
							${fail}
							</div>
						</c:if>
    <form action="changePasswordProcess" method="post" onsubmit="return validatePassword();">
        <div class="form-group">
            <label for="username">UserName</label>
            <input type="text" id="username" name="username" value="${username}" readonly />
        </div>

        <div class="form-group">
            <label for="currentPassword">Current Password</label>
            <input type="password" id="currentPassword" name="password" required />
        </div>
        <div class="form-group">
            <label for="newPassword">New Password</label>
            <input type="password" id="newPassword" name="newPassword" required />
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm New Password</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required oninput="checkPasswordMatch();" />
            <div id="error-message"></div> <!-- Error message display -->
        </div>
        <div class="form-group">
            <button type="submit">Change Password</button>
        </div>
    </form>
</div>
</body>
</html>
