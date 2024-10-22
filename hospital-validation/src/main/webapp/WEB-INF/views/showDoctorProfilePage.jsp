<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .profile-container {
            width: 60%;
            margin: 40px auto;
            background: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 10px;
        }

        .profile-header {
            display: flex;
            align-items: center;
            border-bottom: 1px solid #ddd;
            padding-bottom: 20px;
        }

        .doctor-photo {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            margin-right: 20px;
        }

        .doctor-info h1 {
            margin: 0;
            font-size: 28px;
        }

        .doctor-info p {
            margin: 5px 0;
            color: gray;
        }

        .profile-details {
            margin-top: 20px;
        }

        .profile-details h2 {
            color: #007BFF;
            margin-bottom: 10px;
        }

        .profile-details p {
            line-height: 1.6;
        }

        .button-container {
            text-align: center;
            margin: 20px 0;
        }

        .btn {
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
        }

        .btn:hover {
            background-color: blue;
        }
    </style>
</head>

<body>




    <div class="profile-container">

        <div class="profile-header">
            <div class="doctor-info">
                <c:forEach var="doctor" items="${doctor}">
                    <h1>${doctor.name}</h1>
                    <p class="specialization">${doctor.specialist}</p>
                    <p class="experience">${doctor.experience} Years of Experience</p>
            </div>
        </div>

        <div class="profile-details">
            <h2>About</h2>
                <p>${doctor.name} is a renowned ${doctor.specialist} with over ${doctor.experience} years of experience in the field of health and treatment.</p>

            <h2>Contact Information</h2>
                <p><strong>Email:</strong> <span>${doctor.mail}</span></p>
                <p><strong>Phone:</strong> <span>${doctor.mobile}</span></p>
           
        </div>

        <div class="button-container">
            <%-- <a href="<%= request.getContextPath() %>/bookDoctorAppointment" class="btn">Book Appointment</a> --%>
            <form action="<%= request.getContextPath() %>/bookDoctorAppointment" method="post" style="display: inline;">
								<input type="hidden" name="id" value="${doctor.id}">
								<button type="submit"  class="btn">Book Appointment</button>
					</form>
					<form action="<%= request.getContextPath() %>/doctorChat" method="post" style="display: inline;">
								<input type="hidden" name="mail" value="${doctor.mail}">
								<button type="submit"  class="btn">Message Doctor</button>
					</form>
            
            <a href="<%= request.getContextPath() %>/index" class="btn">Home</a>
        </div>
        
        
        
        
         </c:forEach>
    </div>

</body>

</html>
