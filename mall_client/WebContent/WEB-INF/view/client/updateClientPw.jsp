<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateClientPw</title>
</head>
<body>

	<!-- main Menu -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<%
		Client clientOne = (Client)(request.getAttribute("clientOne"));
	%>
	
	<form action = "<%=request.getContextPath() %>/UpdateClientPwController" method = "post">
		<input type = "hidden" value = "<%=clientOne.getClientMail() %>" name = "clientMail">
		<table border = "1">
			<tr>
				<td>현재 비밀번호</td>
				<td><input type = "password" placeholder = "현재 비밀번호를 입력하세요" name = "currentPw"></td>
			</tr>
			
			<tr>
				<td>새 비밀번호</td>
				<td><intput type = "password" placeholder = "새 비밀번호를 입력하세요" name = "newPw"></td>
			</tr>
		</table>
		<button type = "submit">비번 수정</button>
	</form>

</body>
</html>