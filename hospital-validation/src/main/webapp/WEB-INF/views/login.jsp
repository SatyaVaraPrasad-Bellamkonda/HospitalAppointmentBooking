<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Authentication</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: white;
	color: #333;
	margin: 0;
	padding: 20px;
}

.container {
	max-width: 600px;
	margin: auto;
	padding: 20px;
	border: 1px solid #007BFF;
	border-radius: 5px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	background-color: #f9f9f9;
}

h1 {
	color: #007BFF;
	text-align: center;
}

.button-container {
	text-align: center;
	margin: 20px 0;
}

button {
	background-color: #007BFF;
	color: white;
	border: none;
	border-radius: 5px;
	padding: 10px 20px;
	margin: 10px; /* Adjust margin for spacing */
	cursor: pointer;
	font-size: 16px;
}

button:hover {
	background-color: #0056b3;
}

.form-container {
	display: none; /* Initially hidden */
	margin-top: 20px;
}

.field {
	margin: 15px 0;
}

.label {
	font-weight: bold;
	color: #007BFF;
}

.input {
	width: 100%;
	padding: 8px;
	margin-top: 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 0; /* Sit on top */
	left: 30%	;
	top: 10%;
	width: 40%; /* Full width */
	height: 10%; /* Full height */
	overflow: relative; 
	align:center;/* Enable scroll if needed */
}

.modal-content {
	 background-color: #fefefe;
    margin: auto; /* Center the modal */
    padding: 20px;
    border: 1px solid #888;
    width: 40%; /* Set width for the modal content */
    border-radius: 5px;
    position: relative; /* Positioning relative to modal */
    top: 50%; /* Move down 50% */
    transform: translateY(-50%);
}

.close-button {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close-button:hover, .close-button:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

footer {
	text-align: center;
	margin-top: 20px;
	color: #007BFF;
}
</style>
<script>
        function showForm(role, action) {
            document.querySelectorAll('.form-container').forEach(form => {
                form.style.display = 'none';
            });
            document.getElementById(role + '-' + action + '-form').style.display = 'block';
        }
        function showPopup(message) {
            document.getElementById('alert-message').innerText = message;
            document.getElementById('custom-alert').style.display = 'block';
        }

        function closePopup() {
        	window.location.href ='loginSign';
            document.getElementById('custom-alert').style.display = 'none';
            
        }

        window.onload = function() {
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('error')) {
                showPopup("Invalid credentials. Please try again.");
            }
            if (urlParams.has('logout')) {
                showPopup("Logged Out Succesfully..!");
            }
            <c:if test="${not empty showSignup}">
            showForm('patient', 'signup');
     	   </c:if>
     	 	 <c:if test="${not empty showDoctor}">
          		showForm('doctor', 'signup');
   	   		</c:if>
        }
        /* document.querySelector('form').addEventListener('submit', function(event) {
            const usernameInput = document.getElementById('password');
            if (usernameInput.value.length < 6) {
                alert('Username must be at least 6 characters long.');
                event.preventDefault(); 
            }
        }); */

    </script>

</head>
<body>

	<div class="container">
		<h1>Welcome</h1>
		<div style="color:green; text-align:center">
						<h5>${success}</h5>
		</div>
		<div class="button-container">
			<button onclick="showForm('patient', 'login')">Login</button>
			<button onclick="showForm('patient', 'signup')">Patient
				Signup</button>
				<button onclick="showForm('doctor', 'signup')">Doctor Signup</button>
		</div>
		<!-- <div class="button-container">
			<button onclick="showForm('doctor', 'login')">Doctor Login</button>
			<button onclick="showForm('doctor', 'signup')">Doctor Signup</button>
		</div> -->

		<!-- Patient Login Form -->
		<div id="patient-login-form" class="form-container">
			<h2>Patient / Doctor Login</h2>
			<form action="loginP" method="POST">
				<div class="field">
					<span class="label">Username:</span> <input type="text"
						name="username" class="input" placeholder="Enter your username"
						required>
				</div>
				<div class="field">
					<span class="label">Password:</span> <input type="password"
						name="password" class="input" placeholder="Enter your password"
						required>
				</div>
				<div class="button-container">
					<button type="submit">Login</button>
				</div>
			</form>
		</div>

		<!-- Patient Signup Form -->
		<div id="patient-signup-form" class="form-container">
			<h2>Patient Signup</h2>
			<form action="patientDetailsSubmit" method="POST">
				<!-- Change to your actual URL -->
			

				<div class="field">
					<span class="label">Name:</span> <input type="text"
						name="patient_name" class="input" placeholder="Enter your name" 
						value="${name}" required>
				</div>
				<div class="field">
					<span class="label">Mobile:</span> <input type="tel"
						name="patient_mobile" class="input"
						placeholder="Enter your mobile number" pattern="[0-9]{10}" value="${mobile}" required>
				</div>
				<div class="field">
					<span class="label">Username:</span> <input type="text"
						name="patient_username" class="input"
						placeholder="Choose a username" required>
						
						<c:if test="${not empty errorMessage }">
						<div style="color:red;">
							${errorMessage }
							</div>
						</c:if>
				</div>
				<div class="field">
					<span class="label">Password:</span> 
					<input type="password" name="patient_password" minlength="6" id="password"  class="input"
						placeholder="Create a password" required>
				</div>
				<div class="field">
					<span class="label">Place:</span> <input type="text"
						name="patient_place" class="input" placeholder="Enter your place"
						value="${place}" required>
				</div>
				<div class="field">
					<span class="label">Gender:</span> <select name="patient_gender"
						class="input" value="${gender}" required>
						<option value="">Select Gender</option>
						<option value="Male">Male</option>
						<option value="Female">Female</option>
						<option value="Other">Other</option>
					</select>
				</div>
				<div class="field">
					<span class="label">Age:</span> <input type="number"
						name="patient_age" class="input" value="${age}" placeholder="Enter your age"
						required>
				</div>
				<div class="field">
					<span class="label">Weight (kg):</span> <input type="number"
						name="patient_weight" class="input"
						placeholder="Enter your weight" value="${weight}" required>
				</div>
				<div class="field">
					<span class="label">Temperature (°C):</span> <input type="number"
						name="patient_temp" class="input"
						placeholder="Enter your temperature" step="0.1"  value="${temp}"required>
				</div>
				
				<div class="button-container">
					<button type="submit">Signup</button>
				</div>
			</form>

		</div>

		<!-- Doctor Login Form -->
		<div id="doctor-login-form" class="form-container">
			<h2>Doctor Login</h2>
			<form action="loginP" method="POST">
				<!-- Change to your actual URL -->
				<div class="field">
					<span class="label">Mail ID:</span> <input type="text"
						name="username" class="input" placeholder="Enter your username"
						required>
				</div>
				<div class="field">
					<span class="label">Password:</span> <input type="password"
						name="password" class="input" placeholder="Enter your password"
						required>
				</div>
				<div class="button-container">
					<button type="submit">Login</button>
				</div>
			</form>
		</div>

		<!-- Doctor Signup Form -->
		<div id="doctor-signup-form" class="form-container">
			<h1>Signup</h1>
			<form action="doctorDetailsSubmit" method="POST">
				<!-- Change to your actual URL -->

				<div class="field">
					<span class="label">Name:</span> <input type="text" name="name"
						class="input" placeholder="Enter your name" required>
				</div>
				<div class="field">
					<span class="label">Degree:</span> <input type="text" name="degree"
						class="input" placeholder="Enter your degree" required>
				</div>
				<div class="field">
					<span class="label">Experience (years):</span> <input type="number"
						name="experience" class="input"
						placeholder="Enter years of experience" required>
				</div>
				<div class="field">
					<span class="label">Specialist:</span> <input type="text"
						name="specialist" class="input" placeholder="Enter your specialty"
						required>
				</div>
				<div class="field">
					<span class="label">Mobile:</span> <input type="tel" name="mobile"
						class="input" pattern="[0-9]{10}" placeholder="Enter your mobile number" required>
				</div>
				<div class="field">
					<span class="label">Email:</span> <input type="email" name="mail"
						class="input" placeholder="Enter your email" required>
					<c:if test="${not empty errorMail }">
						<div style="color:red;">
							${errorMail }
							</div>
						</c:if>
				</div>
				<div class="field">
					<span class="label">Password:</span> <input type="password"
						name="password" class="input" minlength="6" placeholder="Create a password"
						required>
				</div>

				<div class="button-container">
					<button type="submit">Signup</button>
				</div>
			</form>
		</div>
		
		

	</div>
	
	 <div id="custom-alert" class="modal">
		<div class="modal-content">
			<span class="close-button" onclick="closePopup()">&times;</span>
			<p id="alert-message">Invalid credentials. Please try again.</p>
		</div>
	</div> 

</body>
</html>
