<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clientOne</title>
</head>
<body>
<%
	// client 리스트 가져오기
	Client c = (Client)request.getAttribute("client");
%>

	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- 메뉴1 -->
	
	<h1>고객정보</h1>
	<table border="1">
		<tr>
			<td>clientNo</td>
			<td><%=c.getClientNo() %></td>
		</tr>

		<tr>
			<td>clientMail</td>
			<td><%=c.getClientMail() %></td>
		</tr>

		<tr>
			<td>clientDate</td>
			<td><%=c.getClientDate() %></td>
		</tr>
	</table>
	<a href=""><button type="button">비밀번호수정</button></a>
	<a href=""><button type="button">회원탈퇴</button></a>

</body>
</html> 
