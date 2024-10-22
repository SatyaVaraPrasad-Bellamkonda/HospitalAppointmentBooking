<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<style>
body {
	font-family: 'Helvetica Neue', Arial, sans-serif;
	background-color: #f9f9f9;
	color: #444;
	margin: 0;
	padding: 20px;
}

.container {
	max-width: 600px; /* Reduced width */
	margin: 0 auto;
	padding: 30px;
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
	text-align: center; /* Center align content */
}

h1 {
	color: #2c3e50; /* Darker Blue */
	margin-bottom: 30px;
	font-size: 2em; /* Slightly smaller */
}

.section {
	margin-bottom: 30px;
	border-radius: 10px;
	padding: 20px;
	background-color: #ecf8ff; /* Light Blue */
}

.section h2 {
	color: #3498db;
	margin-top: 0;
	font-size: 1.5em; /* Slightly smaller */
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 10px;
	text-align: left; /* Align table content to the left */
}

th, td {
	padding: 12px; /* Reduced padding */
}

th {
	background-color: #3498db; /* Light Blue */
	color: #ffffff; /* White */
	font-weight: bold;
}

tr {
	border-bottom: 1px solid #ddd; /* Bottom border for each row */
}

tr:hover {
	background-color: #f0f8ff;
}

.back-button {
	display: inline-block; /* Change to inline-block for centering */
	width: 20%; /* Set width to 20% */
	padding: 10px; /* Adjust padding */
	background-color: #3498db; /* Light Blue */
	color: #ffffff; /* White */
	text-align: center;
	border-radius: 5px;
	text-decoration: none;
	font-size: 1em; /* Slightly smaller */
	transition: background-color 0.3s;
	margin: 30px auto; /* Center button with margin */
}

.back-button:hover {
	background-color: #2980b9; /* Darker Blue */
}
</style>

</head>
<body>
	<div class="container">
		<h1>Patient Portfolio</h1>

		<div class="section">
			<h2>Personal Information</h2>
			<table>
				<tr>
					<th>Name</th>
					<td>${patient.patient_name }</td>
				</tr>

				<tr>
					<th>Mobile</th>
					<td>${patient.patient_mobile}</td>
				</tr>
				<tr>
					<th>Gender</th>
					<td>${patient.patient_gender}</td>
				</tr>
				<tr>
					<th>Age</th>
					<td>${patient.patient_age}years</td>
				</tr>
				<tr>
					<th>Weight</th>
					<td>${patient.patient_weight} kg</td>
				</tr>
				<tr>
					<th>Place</th>
					<td>${patient.patient_place}</td>
				</tr>
			</table>
		</div>

		<!-- <div class="section">
            <h2>Medical History</h2>
            <table>
                <tr>
                    <th>Condition</th>
                    <td>Not Available</td> Replace with actual data
                </tr>
                <tr>
                    <th>Treatments</th>
                    <td>Not Available</td> Replace with actual data
                </tr>
                <tr>
                    <th>Allergies</th>
                    <td>Not Available</td> Replace with actual data
                </tr>
            </table>
        </div>

        <div class="section">
            <h2>Contact Information</h2>
            <table>
                <tr>
                    <th>Email</th>
                    <td>Not Available</td>
                </tr>
                <tr>
                    <th>Emergency Contact</th>
                    <td>Not Available</td> Replace with actual data
                </tr>
            </table>
        </div> -->
		<div class="section">
			<h3>Patient Record</h3>
			<c:if test="${not empty image}">
				<img src="data:${imageType};base64,${image}" alt="User Image" />
			</c:if>

			<c:if test="${not empty error }">
				<div style="color: red;">
					<strong>${error }</strong>
				</div>
			</c:if>
		</div>

		<a href="<%=request.getContextPath()%>/dashboard" class="back-button">Back</a>
	</div>
</body>
</html>
