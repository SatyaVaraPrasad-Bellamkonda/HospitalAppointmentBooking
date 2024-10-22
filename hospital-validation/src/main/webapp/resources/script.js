
document.getElementById('viewActiveDoctors').addEventListener('click', function() {
	window.location.href = 'activeDoctors';
});


document.getElementById('viewDbDataButton').addEventListener('click', function() {
	window.location.href = 'completeDoctors';
});
/*document.getElementById('profile').addEventListener('click', function() {
	window.location.href = 'userProfile';
});*/
document.getElementById('logout').addEventListener('click', function() {
	window.location.href = 'logout';
});
document.getElementById('changePasswordButton').addEventListener('click', function() {
	window.location.href = 'changePassword';
});
document.getElementById('upload').addEventListener('click', function() {
	window.location.href = 'image';
});
document.getElementById('viewRecords').addEventListener('click', function() {
	window.location.href = 'viewRecords';
});
document.getElementById('viewMessages').addEventListener('click', function() {
	window.location.href = 'viewMessages';
});
document.getElementById('dashboard').addEventListener('click', function() {
	window.location.href = 'dashboard';
});
document.getElementById('insertButton').addEventListener('click', function() {
	window.location.href = 'insertDoctor';
});
document.getElementById('deleteButton').addEventListener('click', function() {
	window.location.href = 'deleteDoctor';
});
document.getElementById('updateButton').addEventListener('click', function() {
	window.location.href = 'updateDoctor';
});
document.getElementById('viewRequests').addEventListener('click', function() {
	window.location.href = 'viewDoctorRequests';
});


		/*function showPopup(message) {
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
        }*/