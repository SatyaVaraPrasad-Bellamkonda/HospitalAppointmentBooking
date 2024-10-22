<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
<title>User Image</title>
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/views/index.jsp"%>
	</div>
	<h1>Medical Record</h1>

	<c:if test="${not empty image}">
		<img src="data:${imageType};base64,${image}" alt="User Image" />
	</c:if>

	<c:if test="${not empty error }">
		<div style="color: red;">
			<strong>${error }</strong>
		</div>
	</c:if>
</body>
</html>
