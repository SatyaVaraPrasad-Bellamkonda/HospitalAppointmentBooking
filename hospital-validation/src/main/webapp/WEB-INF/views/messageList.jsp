<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Messages</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px auto;
        }
        .message-button {
            display: block;
            margin: 10px 0;
            padding: 10px;
            background-color: white;
            color: black;
            border: 1px solid blue;
            width:100%;
            border-radius: 5px;
            cursor: pointer;
            text-align: left;
            font-size: 22px;
        }
        .message-button:hover {
            background-color: #FFF5EE;
        }
        .timestamp {
            font-size: 0.9em;
            color: gray;
        }
    </style>
</head>
<body>
<div>
		<%@ include file="/WEB-INF/views/index.jsp"%>
	</div> 

<h1>User Messages</h1>

<div id="messages">
    <c:forEach var="message" items="${messages}">
        <form action="showMessages" method="post">
            <input type="hidden" name="sender" value="${message}" />
            <input type="hidden" name="receiver" value="${receiver}" />
            <button type="submit" class="message-button">
                <strong>From : </strong> ${message} <br>
               <%--  <strong>Message:</strong> ${message} <br>
                <span class="timestamp"><strong>Received at:</strong> ${message}</span> --%>
            </button>
        </form>
    </c:forEach>
</div>

</body>
</html>
