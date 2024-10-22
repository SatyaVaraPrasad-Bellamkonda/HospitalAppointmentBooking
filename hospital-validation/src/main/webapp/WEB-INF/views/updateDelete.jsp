<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Data</title>
 <style>
        body, h3, .form-form, .form-type {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    padding: 20px;
}

h3 {
    color: #333;
    margin-bottom: 20px;
}

.form-form {
    display: inline-block;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 90%; /* Adjust width for smaller screens */
    max-width: 400px; /* Maximum width for larger screens */
    text-align: left;
    margin: 0 auto; /* Center the form horizontally */
}

.form-type[type="number"], .form-type[type="submit"] {
    width: 100%;
    box-sizing: border-box;
}

.form-type[type="number"] {
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 16px;
}

.form-type[type="submit"] {
    background-color: #dc3545; /* Red color for delete action */
    color: white;
    border: none;
    padding: 10px;
    font-size: 16px;
    cursor: pointer;
    border-radius: 4px;
    transition: background-color 0.3s, transform 0.2s;
    width: 100%;
}

.form-type[type="submit"]:hover {
    background-color: #c82333;
    transform: scale(1.05);
}

.form-type[type="submit"]:active {
    background-color: #a71d2a;
}



/* Responsive styles */
@media (max-width: 768px) {
    .form-form {
        width: 95%; /* Increase form width on smaller screens */
        padding: 15px;
    }

    .form-type[type="submit"] {
        padding: 12px; /* Increase padding for better touch targets */
    }
}

@media (max-width: 480px) {
    .form-form {
        width: 100%;
        padding: 10px;
    }

    h3 {
        font-size: 18px; /* Adjust font size for smaller screens */
        margin-bottom: 15px;
    }

    .form-type[type="submit"] {
        padding: 15px;
    }
}

    </style>
</head>
<body>
<div>
	<%@ include file="/WEB-INF/views/index.jsp" %>
</div>

<div>
<h3>Enter the id of Doctor to ${message }:</h3>
<form action="${message}DoctorStatus" method="post" class="form-form">
<input type="number" name="id" class="form-type" >
<input type="submit" value="Confirm ${message }" class="form-type">
</form>
		<c:if test="${not empty noData }">
			<div style="color: red;">
				<strong>${noData }</strong>
			</div>
		</c:if>
		<c:if test="${not empty success }">
			<div style="color: green;">
				<strong>${success }</strong>
			</div>
		</c:if>
	
	</div>

</body>
</html>