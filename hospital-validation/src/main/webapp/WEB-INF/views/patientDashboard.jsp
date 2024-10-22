<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>


<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>Appointment system</title>
</head>

<style>
nav, thead {
	background-color: #007BFF;
}

.navbar-brand {
	font-weight: bold;
	color: white !important;
}

.head1, .fa {
	color: rgb(5, 182, 5);
	font-weight: bold;
}

.form-input {
	border-radius: 4px !important;
	max-width: 1000px;
	height: auto;
	margin: auto;
	border: 1px solid rgb(5, 182, 5) !important;
}

label {
	text-align: left !important;
	color: black;
	font-weight: 500;
	margin-bottom: 5px;
}

.btn {
	background-color: rgb(5, 182, 5) !important;
	font-weight: 600;
}

.home-btn {
	background-color: #007bff;
	color: white;
	border: none;
	font-size: 14px;
	cursor: pointer;
	transition: background-color 0.3s, transform 0.2s;
	padding: 2px 5px; /* Padding */
	text-align: center; /* Centered text */
	text-decoration: none; /* Remove underline */
	display: inline-block; /* Make the button inline with other elements */
	margin: 2px; /* Margin around buttons */
	border-radius: 5px; /* Rounded corners */
	transition: background-color 0.3s ease, transform 0.3s ease;
	/* Smooth transitions */
}

.home-btn:hover {
	background-color: #007bff; /* Darker green background on hover */
	transform: scale(1.05); /* Slightly enlarge the button */
}

#dashboard_link {
	text-align: center;
	color: rgb(5, 182, 5) !important;
	background-color: white !important;
}
</style>

<body>

	<div>
		<%@ include file="/WEB-INF/views/index.jsp"%>
	</div>


	<main>

		<br>
		<div class="container">
			<div class="table-responsive">
				<table class="table table-bordered">
					<thead class="text text-light">
						<th>Appointment Id</th>
						<th>Doctor Mail</th>	
						<th>ServiceType</th>
						<th>Appointment Date</th>
						<th>Required Time</th>
						<th >Status</th>
						<th> Withdraw</th>
					</thead>
					<tbody>
						<c:forEach var="appointment" items="${appointments}">
							<tr>
								<td>${appointment.id }</td>
								<td>${appointment.doctormail}</td>
								<td>${appointment.service}</td>
								<td>${appointment.date}</td>
								<td>${appointment.duration}minutes</td>
								<td>
								<form action="approvePatientById" method="POST"
										style="display: inline;">
										<input type="hidden" name="id" value="${appointment.id}">
										<c:choose>
											<c:when test="${appointment.status == 0}">
												<bold style="color: #FF4500;">Pending</bold>
											</c:when>
											<c:when test="${appointment.status == 1}">
												
												<Strong style="background-color: lightgreen;">Doctor Approved Your Appointment</strong>
											</c:when>
											<c:when test="${appointment.status == 2}">
												<Strong style="color: red;">Rejected By Doctor</strong>
											</c:when>
											<c:when test="${appointment.status == 3}">
												<Strong style="color: red;">You WithDrawed Your Appointment</strong>
											</c:when>
										</c:choose>
									</form>
									<td>
									<form
										action="<%=request.getContextPath()%>/withdrawPatient"
										method="post" style="display: inline;">
										<input type="hidden" name="id" value="${appointment.id}">
										<button type="submit" class="home-btn">Withdraw</button>
									</form>
									</td>
								</td>
								
								<%-- <td>
									<form action="showPatientProfile" method="GET"
										style="display: inline;">
										<input type="hidden" name="patient_name"
											value="${appointment.patient_name}">
										<button type="submit" class="home-btn">View Profile</button>
									</form>
								</td> --%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</main>

</body>

</html>
