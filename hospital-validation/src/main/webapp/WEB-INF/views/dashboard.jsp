<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>


<head>
<!-- <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"> --></script>
<meta charset="UTF-8">
<title>Appointment system</title>
</head>

<style>
/* Table layout */
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    border: 1px solid #ddd;
    padding: 10px;
    text-align: left;
}

th {
    background-color: #007BFF;
    color: #fff;
    text-align: center;
}

td {
    background-color: #f9f9f9;
}

/* Table header */
thead th {
    font-weight: bold;
}

/* Table rows */
tbody tr {
    border-bottom: 1px solid #ddd;
}

tbody tr:hover {
    background-color: #f2f2f2;
}



td:nth-child(10),td:nth-child(9) {
   text-align: center;
}


/* Buttons */
.home-btn {
    background-color: #007bff;
    color: white;
    border: none;
    font-size: 14px;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.2s;
    padding: 2px 5px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    margin: 2px;
    border-radius: 5px;
    transition: background-color 0.3s ease, transform 0.3s ease;
}

.home-btn:hover {
    background-color: #0056b3;
    transform: scale(1.05);
}

/* Email links */
.email-link {
    color: blue;
    text-decoration: none;
    padding: 5px;
    border: 1px solid transparent;
    transition: background-color 0.3s;
}

.email-link:hover {
    background-color: lightgray;
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
						<th>SI.NO</th>
						<th>Patient Name</th>
						<th>Email</th>
						<c:if test="${not empty doctor }">
							<div style="color: red;">
								<th>Doctor Mail</th>
							</div>
						</c:if>



						<th>Phone</th>
						<th>Address</th>
						<th>ServiceType</th>
						<th>Appointment Date</th>
						<th>Required Time</th>
						<th colspan="2">Status</th>

						<th>View Info</th>
						<th>Records</th>
						<th>Message</th>
					</thead>
					<tbody>
						<c:forEach var="appointment" items="${appointments}">
							<tr>
								<td>${appointment.id }</td>
								<td>${appointment.patient_name}</td>
								<td><a href="mailto:${appointment.email}"
									class="email-link">${appointment.email}</a></td>
								<c:if test="${not empty doctor }">
									<div style="color: red;">
										<td><a href="mailto:${appointment.doctormail}"
									class="email-link">${appointment.doctormail}</a></td>
									</div>
								</c:if>

								<td>${appointment.patient_mobile}</td>
								<td>${appointment.patient_place}</td>
								<td>${appointment.service}</td>
								<td>${appointment.date}</td>
								<td>${appointment.duration}minutes</td>
							
								
								
								<c:choose>
									<c:when test="${appointment.status != 3}">
										<td>
											<form action="approvePatientById" method="POST"
												style="display: inline;">
												<input type="hidden" name="status" value="1"> <input
													type="hidden" name="id" value="${appointment.id}">
												<c:choose>
													<c:when test="${appointment.status == 1}">
														<button type="submit" class="home-btn">Accepted
															Patient</button>
													</c:when>
													<c:otherwise>
														<button type="submit" class="home-btn">Approve</button>
													</c:otherwise>
												</c:choose>
											</form>
										</td>
										<td>
											<form action="approvePatientById" method="POST"
												style="display: inline;">
												<input type="hidden" name="status" value="2"> <input
													type="hidden" name="id" value="${appointment.id}">
												<c:choose>
													<c:when test="${appointment.status == 2}">
														<button type="submit" class="home-btn">Rejected
															By You</button>
													</c:when>
													<c:otherwise>
														<button type="submit" class="home-btn">Reject</button>
													</c:otherwise>
												</c:choose>
											</form>
										</td>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${appointment.status == 3}">
										<td colspan="2"><Strong style="color: red;">Patient
												Withdrawed</strong></td>
									</c:when>
								</c:choose>
								<td>
									<form action="showPatientProfile" method="GET"
										style="display: inline;">
										<input type="hidden" name="patient_name"
											value="${appointment.patient_name}">
										<button type="submit" class="home-btn">View Profile</button>
									</form>
								</td>
								<td><form action="showAppointmentById" method="GET"
										style="display: inline;">
										<input type="hidden" name="patient_name"
											value="${appointment.patient_name}">
										<button type="submit" class="home-btn">View Record</button>
									</form></td>
									<td><form action="patientChat" method="GET"
										style="display: inline;">
										<input type="hidden" name="patient_name"
											value="${appointment.patient_name}">
										<button type="submit" class="home-btn">Chat</button>
									</form></td>
							</tr>
							
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</main>

</body>

</html>
