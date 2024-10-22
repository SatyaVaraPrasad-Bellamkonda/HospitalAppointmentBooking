<%--  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>  --%>
    <%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%> 
    
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <style>
        <%@include file="/resources/style.css" %>
     
    </style>
</head>
<body>
    <div class="taskbar">
    
        <button id="viewActiveDoctors">Active Doctors</button>
        <sec:authorize access="hasAuthority('admin')"> 
        <button id="insertButton">Add Doctor</button>
        <button id="deleteButton">Delete Doctor</button>
        <button id="updateButton">Update Doctor</button>
        <button id="viewRequests">Requests</button>
        </sec:authorize>
        <button id="viewDbDataButton">Complete Doctors</button>
        <div> 
        <button id="dashboard">DashBoard</button>
        
      
      </div> 
        <div class="dropdown">
            <button id="profile">Profile</button>
            <div class="dropdown-content">  
                <button id="changePasswordButton">Change Password</button>
            </div>
        </div>
          <button id="upload">Upload</button>
          <button id="viewRecords">Records</button>
          <button id="viewMessages">Messages</button>

        <button id="logout">Logout</button>
        
        <div class="search-bar">
            <form action="searchDoctor" class="search-bar">
                <input type="text" class="textid" name="id" placeholder="Search for Doctors...">
                <input type="submit" placeholder="Submit">
            </form> 
        </div>
        <!-- <div id="custom-alert" class="modal">
		<div class="modal-content">
			<span class="close-button" onclick="closePopup()">&times;</span>
			<p id="alert-message">Invalid credentials. Please try again.</p>
		</div>
	</div> -->
    </div>
    <script type="text/javascript"><%@include file="/resources/script.js" %></script>
</body>
</html>
