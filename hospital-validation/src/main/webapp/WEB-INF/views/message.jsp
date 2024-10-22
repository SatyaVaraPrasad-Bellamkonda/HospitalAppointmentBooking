<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat ${name }</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fa;
            margin: 0;
            padding: 20px;
        }
       .chat-container {
    max-width: 600px;
    margin: 50px auto; /* Increased top margin */
    background-color: #ffffff;
    border-radius: 8px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}
        .myHead {
            background-color: #007bff;
            color: white;
            padding: 15px;
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
            text-align: center;
        }
        .chat-box {
            padding: 15px;
            max-height: 400px;
            overflow-y: auto;
            text-align:left;
        }
        .message {
            margin: 10px 0;
            padding: 10px;
            border-radius: 20px;
            max-width: 70%;
        }
        .patient {
            background-color: #e7f3ff;
            align-self: flex-start;
        }
        .doctor {
            background-color: #d1e7dd;
            align-self: flex-end;
        }
        .input-container {
            display: flex;
            padding: 15px;
            border-top: 1px solid #e0e0e0;
        }
        .input-field {
            flex: 1;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 20px;
            margin-right: 10px;
        }
        .send-button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 20px;
            cursor: pointer;
            transition: background-color 0.3s;
             text-decoration: none;
        }
        .send-button:hover {
            background-color: #0056b3;
        }
         
    </style>
</head>
<body class="myBody">
<div>
		<%@ include file="/WEB-INF/views/index.jsp"%>
	</div> 
    <div class="chat-container">
        <div class="myHead">
            <h3>Chat with : ${name}</h3>
        </div>
        <div class="chat-box">
            <c:forEach var="message" items="${messages}">
                 <div class="message ${message.sender == sender ? 'sender' : 'receiver'}">
                <strong>${message.sender == sender ? 'You' : name}</strong>
                    <span>${message.message}</span>
                    <%-- <span>${message.timestamp}</span> --%>
                </div>
            </c:forEach>
        </div>
        <form action="<%= request.getContextPath() %>/sendMessage" method="post" class="input-container">
            <input type="hidden" name="sender" value="${sender}">
            <input type="hidden" name="receiver" value="${receiver}">
            <input type="text" name="message" placeholder="Type your message..." class="input-field" required>
            	<c:if test="${not empty docId }">
									<div style="color: red;">
										<input type="hidden" name="docId" value="${docId}">
									</div>
								</c:if>
            <input type="hidden" name="docId" value="0">
            <button type="submit" class="send-button">Send</button>
                    <a href="<%= request.getContextPath() %>/index" class="send-button">Home</a>
        </form>
        
    </div>
</body>
</html>
