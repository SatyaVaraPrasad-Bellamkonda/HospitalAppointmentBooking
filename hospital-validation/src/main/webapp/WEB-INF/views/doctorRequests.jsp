<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Doctor Requests</title>

<style>
table {
	width: 100%;
	border-collapse: collapse;
	margin: 20px 0;
}

th {
	background-color: #f2f2f2;
	color: #333;
	padding: 10px;
	text-align: center;
	border-bottom: 2px solid #ddd;
}

td {
	border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

table, th, td {
	border: 1px solid #ddd;
}

th {
	font-size: 1.1em;
}

tr:hover {
	background-color: #f1f1f1;
}

.home-btn {
	background-color: #007bff;
	color: white;
	border: none;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s, transform 0.2s;
	padding: 5px 32px; /* Padding */
	text-align: center; /* Centered text */
	text-decoration: none; /* Remove underline */
	display: inline-block; /* Make the button inline with other elements */
	margin: 10px; /* Margin around buttons */
	border-radius: 5px; /* Rounded corners */
	transition: background-color 0.3s ease, transform 0.3s ease;
	/* Smooth transitions */
}

.home-btn:hover {
	background-color: #007bff; /* Darker green background on hover */
	transform: scale(1.05); /* Slightly enlarge the button */
}
</style>


</head>
<body>
	<div>
		<%@ include file="/WEB-INF/views/index.jsp"%>
	</div>
	<div>
		<h2>${message}</h2>


		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Degree</th>
					<th>Experience</th>	
					<th>Specialist</th>
					<th>Mobile</th>
					<th>Email</th>
					<!-- <th>UserName</th>
					<th>Role</th> -->
					<th>Approval</th>
				</tr>

			</thead>
			<c:forEach var="eachValue" items="${doctors }">



				<tbody>
					<tr>
						<td>${eachValue.id}</td>
						<td>${eachValue.name}</td>
						<td>${eachValue.degree}</td>
						<td>${eachValue.experience}</td>
						<td>${eachValue.specialist}</td>
						<td>${eachValue.mobile}</td>
						<td>${eachValue.mail}</td>

						<td><form action="<%= request.getContextPath() %>/updateDoctorStatus" method="post">
								<input type="hidden" name="id" value="${eachValue.id}">
								<button type="submit" class="home-btn">Approve</button>
							</form></td>
					</tr>
				</tbody>


			</c:forEach>
		</table>

		<button id="homeButton" class="home-btn">Go Home</button>
		<br>
	</div>
	<script>
		document.getElementById('homeButton').addEventListener('click',
				function() {
					window.location.href = 'index';
				});
		<%-- document.getElementById('approval').addEventListener('click',
				function() {
					window.location.href = '<%= request.getContextPath() %>/approveDoctorSignUp';
				}); --%>
	</script>
</body>
</html>