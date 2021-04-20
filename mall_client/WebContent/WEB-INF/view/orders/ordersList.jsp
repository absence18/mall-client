<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>

	<h1>주문리스트</h1>
	<table border="1">
		<tr>
			<td>ordersNo</td>
			<td>ebookNo</td>
			<td>ordersDate</td>
			<td>ordersState</td>
			<td>ebookTitle</td>
			<td>ebookPrice</td>
		</tr>
		
	<c:forEach var="mo" items="${ordersList }">

		<tr>
			
			<td>${mo.ordersNo }</td>
			<td>${mo.ebookNo }</td>
			<td>${mo.ordersDate.substring(0,11) }</td>
			<td>${mo.ordersState }</td>
			<td>${mo.ebookTitle }</td>
			<td>${mo.ebookPrice }원</td>
			
		</tr>
		
	</c:forEach>

	</table>
</body>
</html>