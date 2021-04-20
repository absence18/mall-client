<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cartList</title>
</head>
<body>

	<!-- 메인메뉴 -->
	<div>
		<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>
	<!-- cartList -->
	<h1>CartList</h1>
	<table border="1">
		<tr>
			<td>cartNo</td>
			<td>ebookNo</td>
			<td>ebookTitle</td>
			<td>cartDate</td>
			<td>삭제</td>
			<td>주문</td>
		</tr>
	
		<c:forEach var="m" items="${cartList }">
			<tr>
				<td>${m.cartNo }</td>
				<td>${m.ebookNo }</td>
				<td>${m.ebookTitle }</td>
				<td>${m.cartDate.substring(0,11) }</td>
				<!-- DeleteCartController -> CartDao.deleteCart() -> redirect:CartListController -->
				<td><a href="${pageContext.request.contextPath }/DeleteCartController?cartNo=${m.ebookNo }">삭제</a></td>
				<!-- InsertOrdersController -> insertOrders(),deleteCart()::ISSUE 트렌처리 -> redirect:OrdersListController -->
				<td><a href="${pageContext.request.contextPath }/InsertOrdersController?ebookNo=${m.ebookNo }">주문</a></td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>