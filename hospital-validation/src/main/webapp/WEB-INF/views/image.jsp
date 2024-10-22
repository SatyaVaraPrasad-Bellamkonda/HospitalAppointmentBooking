<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Image Upload</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	display: flex-start;
	justify-content: center;
	padding-top: 20px;
	padding-left: 40%;
	align-items: flex-start; /* Change to flex-start to move the form up */
}

h2 {
	text-align: center; /* Center the heading text */
}

.prasad {
	background-color: #fff;
	padding: 10px;
	border-radius: 5px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	max-width: 400px;
	width: 100%; /* Makes the form responsive */
}

.prasad input[type="file"] {
	display: block;
	margin: 0 auto 15px auto; /* Centering the input with auto margins */
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
	width: 90%; /* Set a specific width */
}

.pspk {
	background-color: #007bff;
	color: white;
	border: none;
	padding: 10px 15px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
	transition: background-color 0.3s;
	width: 90%; /* Set a specific width */
	margin: 0 auto; /* Center the button */
}

.pspk button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>

	<div>
		<%@ include file="/WEB-INF/views/index.jsp"%>
	</div>

	<form action="upload" method="post" class="prasad"
		enctype="multipart/form-data">
		<h2>Upload Image</h2>
		<br />
		<c:if test="${not empty success }">
			<div style="color: green;">${success }</div>
		</c:if>
		<c:if test="${not empty fail }">
			<div style="color: red;">${fail }</div>
		</c:if>
		<c:if test="${not empty unsupport }">
			<div style="color: red;">${unsupport }</div>
		</c:if>
		<input type="file" name="file" required>
		<button class="pspk" type="submit">Upload</button>
	</form>

	<!-- <h2>Images</h2> -->
	<%-- <c:forEach var="image" items="${images}">
    <img src="data:image/jpeg;base64,${fn:escapeXml(image.data)}" width="200" height="200" />
</c:forEach> --%>
</body>
</html>
