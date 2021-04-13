<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cartList</title>
</head>
<body>
	
	<!-- main Menu -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<!-- cart List -->
	<h1>cartList</h1>
	
	<!-- 출력 폼 -->
	<table border = "1">
		<thead>
			<tr>
				<th>cartNo</th>
				<th>ebookNo</th>
				<th>ebookTitle</th>
				<th>cartDate</th>
			</tr>
		</thead>
		
		<!-- 값 가져오기 -->
	<%
		List<Map<String,Object>> cartList = (List<Map<String,Object>>)(request.getAttribute("cartList"));
		for(Map<String,Object> mso : cartList){
	%>
		
		<tbody>
			<tr>
				<td><%=mso.get("cartNo") %></td>
				<td><%=mso.get("ebookNo") %></td>
				<td><%=mso.get("ebookTitle") %></td>
				<td><%=mso.get("cartDate") %></td>
			</tr>
		</tbody>
	<%
		}
	%>
	</table>
	
</body>
</html>