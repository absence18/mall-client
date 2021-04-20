<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- 메뉴1 -->

	<h1>고객정보</h1>
	<table border="1">
	
		<tr>
			<td>categoryName</td>
			<td>${ebook.categoryName }</td>
		</tr>

		<tr>
			<td>ebookISBN</td>
			<td>${ebook.ebookISBN }</td>
		</tr>

		<tr>
			<td>ebookTitle</td>
			<td>${ebook.ebookTitle }</td>
		</tr>
		
	</table>
	<!-- InsertCartController?ebookNo - CartDao.insertCart() - redirect:CartListController -->
	<a href="${pageContext.request.contextPath }/InsertCartController?ebookNo=${ebook.ebookNo }">
	
		<c:choose>
			<c:when test="${loginClient == null || ebook.ebookState == '품절' || ebook.ebookState == '절판' || ebook.ebookState == '구편절판'}">
			
				<button type="button" disabled="disabled">장바구니추가</button>
				
			</c:when>
			
			<c:otherwise>
				<button type="button">장바구니추가</button>
			</c:otherwise>
		
		</c:choose>
	
	</a>
</body>
</html>
