 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add Details Here</title>
    <style>
        body, h2, form, input {
          
            box-sizing: border-box;
        }
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 20px;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        .formStyle {
            display: inline-block;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: left;
        }
        .inputField,select{
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        input::placeholder {
            color: #888;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s, transform 0.2s;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
            transform: scale(1.05);
        }
        input[type="submit"]:active {
            background-color: #00408d;
        }
/* Example for fixed header */
body {
    padding-top: 60px; /* Adjust based on header height */
}

/* If header is not fixed, ensure proper layout */
.header {
    width: 100%;
    background: #f1f1f1; /* Example header background */
    padding: 10px;
}

    </style>
</head>
<body>
	<div>
	<%@ include file="/WEB-INF/views/index.jsp" %>
	</div>
    <div class="content">
        <h2>Add the Details Below</h2>
        <form action="doctorDetailsSubmit" method="post" class="formStyle">
            <input type="text" name="name" placeholder="Name" class="inputField" required><br><br>
            <input type="text" name="degree" placeholder="Degree"  class="inputField" required><br><br>
            <input type="number" name="experience" placeholder="Experience" class="inputField" required><br><br>
            <input type="text" name="specialist" placeholder="Specialization" class="inputField" required><br><br>
            <input type="text" name="mobile" placeholder="Mobile" pattern="[0-9]{10}" class="inputField" required><br><br>
            <input type="email" name="mail" placeholder="Mail Id" class="inputField" required><br><br>
            <input type="password" name="password" placeholder="Password" class="inputField" required><br><br>
            <label for="locked">Status:</label>
        	<select id="booleanValue" name="locked" >
            <option value="false">Active</option>
            <option value="true">Lock</option>
        	</select>
            <input type="text" name="authority" placeholder="Authority" class="inputField" required><br><br>
            
            <input type="submit" value="Submit Details">
        </form>
    </div>
</body>
</html>
