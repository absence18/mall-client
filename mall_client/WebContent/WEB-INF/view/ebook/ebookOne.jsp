<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Ebook ebook = (Ebook)request.getAttribute("ebook");
%>

	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- 메뉴1 -->

	<h1>고객정보</h1>
	<table border="1">
		<tr>
			<td>categoryName</td>
			<td><%=ebook.getCategoryName()%></td>
		</tr>

		<tr>
			<td>ebookISBN</td>
			<td><%=ebook.getEbookISBN()%></td>
		</tr>

		<tr>
			<td>ebookTitle</td>
			<td><%=ebook.getEbookTitle()%></td>
		</tr>
		<!-- 추가 tr.... -->
	</table>
	<!-- InsertCartController?ebookNo - CartDao.insertCart() - redirect:CartListController -->
	<a href="<%=request.getContextPath()%>/InsertCartController?ebookNo=<%=ebook.getEbookNo()%>">
		<%
			if(session.getAttribute("loginClient") == null 
				|| ebook.getEbookState().equals("품절")
				|| ebook.getEbookState().equals("절판")
				|| ebook.getEbookState().equals("구편절판")) {
		%>
				<button type="button" disabled="disabled">장바구니추가</button>
		<%		
			} else {
		%>
				<button type="button">장바구니추가</button>
		<%		
			}
		%>		
	</a>
</body>
</html>
