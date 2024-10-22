<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Doctor</title>
<style>
body, h2, form, input {
	margin: 0;
	padding: 0;
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

.form-form {
	display: inline-block;
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	width: 300px;
	text-align: left;
}

.form-form input[type="number"], .form-form input[type="text"],
	.form-form input[type="tel"], .form-form input[type="email"] {
	width: calc(100% - 22px);
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ddd;
	border-radius: 4px;
	font-size: 16px;
}

.form-form input::placeholder {
	color: #888;
}

.form-form input[type="submit"] {
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

.form-form input[type="submit"]:hover {
	background-color: #0056b3;
	transform: scale(1.05);
}

.form-form input[type="submit"]:active {
	background-color: #00408d;
}

select {
	width: 90%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
	transition: border-color 0.3s;
}

select:focus {
	border-color: #007bff;
	outline: none;
}

option {
	padding: 10px; /* Padding inside options for better touch targets */
}
</style>
</head>
<body style="text-align: center">

	<div>
		<%@ include file="/WEB-INF/views/index.jsp"%>
	</div>
	<div>
		<h2>Edit Doctor Information</h2>
		<h2>${message }</h2>
		<c:if test="${not empty success }">
			<div style="color: green;">
				<strong>${success }</strong>
			</div>
		</c:if>

		<c:forEach var="eachValue" items="${user}">
			<form action="updateDoctorPost" method="post" class="form-form">
				<input type="hidden" name="id" value="${eachValue.id}" /> 
				<labelfor="name">Name:</label>
				 <input type="text" id="name" name="name" value="${eachValue.name}" required /><br /> 
				 <label for="mobile">Degree:</label>
				<input type="text" id="mobile" name="degree"
					value="${eachValue.degree}" required /><br /> 
					<label for="mail">Experience:</label>
				<input type="number" id="email" name="experience"
					value="${eachValue.experience}" required /><br />
					 <label	for="batch">Specialization:</label> 
					 <input type="text" id="email" name="specialist" value="${eachValue.specialist}" required /><br />
				<label for="place">Mobile:</label> <input type="tel" id="mobile"
					pattern="[0-9]{10}" name="mobile" value="${eachValue.mobile}"
					required /><br /> <label for="mentor">Mail ID:</label> <input
					type="email" id="mobile" name="mail" value="${eachValue.mail}"
					required />
				<c:if test="${not empty errorMail }">
					<div style="color: red;">
						${errorMail }<br/><br/>
					</div>
				</c:if>
				<c:choose>
					<c:when test="${eachValue.authority != null}">
						<label for="mentor">Authority:</label>
						<select id="role" name="authority" required>
							<option value="${eachValue.authority}" selected>${eachValue.authority}</option>
							<option value="user">User</option>
							<option value="admin">Admin</option>
						</select>
						<br />
					</c:when>
					<c:otherwise>
						<label for="mentor">Authority:</label>
						<select id="role" name="authority" required>
							<option value="" disabled selected>Select Authority</option>
							<option value="user">User</option>
							<option value="admin">Admin</option>
						</select>
						<br />


					</c:otherwise>
				</c:choose>

				<label for="locked">Status:</label> <select id="booleanValue"
					name="locked">
					<option value="" disabled selected>Select Status</option>
					<option value="false">Active</option>
					<option value="true">Lock</option>
				</select> <input type="submit" value="Update" />
			</form>
		</c:forEach>
	</div>


</body>
</html>