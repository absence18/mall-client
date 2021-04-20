<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertClient</title>
</head>
<body>
	<div>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>
	<h1>회원가입</h1>
	<form action="${pageContext.request.contextPath }/InsertClientController" method="post">
		<div> clientMail : 
			<input type="text" name="clientMail" required="required">
		</div>

		<div> clientPw : 
			<input type="password" name="clientPw" required="required">
		</div>
		<button type="submit">입력</button>
	</form>
</body>
</html> 