<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <title>Book Appointment</title>
    <style>
        body {
            background-color: #f8f9fa; /* Light background */
        }
        .form-container {
            background-color: white; /* White background for form */
            border: 1px solid #007bff; /* Blue border */
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: auto; /* Center the form */
        }
        .btn {
            background-color: #007bff; /* Blue button */
            color: white;
        }
        .btn:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }
    </style>
    

</head>
<body>
<div>
	<%@ include file="/WEB-INF/views/index.jsp" %>
</div>

<div class="form-container mt-5">
		<c:if test="${not empty success }">
			<div style="color: green;">
				<strong>${success }</strong>
			</div>
		</c:if>
		<c:if test="${not empty dash }">
			<div style="color: green;">
				${dash }
			</div>
		</c:if>
		
		<h2 class="text-center">Book Your Appointment</h2>
    <form action="updateAppointment" method="post">
        <label for="userName"><i class="fa fa-user"></i> User Name</label>
        <input type="text" name="patient_name" value="${name}" class="form-control mb-3" readonly>
         <label for="doctorname"><i class="fa fa-user"></i> Doctor You Are Consulting</label>
        <input type="text"  value="${doctorname}" class="form-control mb-3" readonly>
         <!-- <label for="doctormail"><i class="fa fa-user"></i> Doctor You Are Consulting</label> -->
        <input type="hidden"  name="doctormail" value="${doctormail}" class="form-control mb-3" readonly>

        <label for="email"><i class="fa fa-envelope"></i> Email Address</label>
        <input type="email" name="email" class="form-control mb-3" required>

        <label for="phone"><i class="fa fa-phone"></i> Phone Number</label>
        <input type="tel" name="patient_mobile" pattern="[0-9]{10}" class="form-control mb-3" required>

        <label for="address">Address</label>
        <input type="text" name="patient_place"  class="form-control mb-3" required>

        <label for="serviceType">Service Type</label>
        <select name="service" class="form-control mb-3" required>
            <option value="">Select service</option>
            <option value="General">General</option>
            <option value="Emergency">Emergency</option>
            <option value="Online">Online</option>
            <option value="others">Others</option>
        </select>

        <label for="appointmentDate"><i class="fa fa-clock-o"></i> Appointment Date</label>
	<input type="date" name="date" class="form-control mb-3"  id="appointmentDate" required>
	<c:if test="${not empty fail }">
			<div style="color: red;">
				${fail }
			</div>
		</c:if>

        <label for="appointmentDuration">Required Time</label>
        <select name="duration" class="form-control mb-3" required>
            <option value="">Select duration</option>
            <option value="30">30 min</option>
            <option value="45">45 min</option>
            <option value="60">60 min</option>
            <option value="120">2 hours</option>
        </select>

        

        <button class="btn btn-block" type="submit">Book Appointment</button>
    </form>
</div>

<script>
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    const formattedDate = `${year}-${month}-${day}`;
    document.getElementById('appointmentDate').setAttribute('min', formattedDate);
</script>

</body>
</html>
