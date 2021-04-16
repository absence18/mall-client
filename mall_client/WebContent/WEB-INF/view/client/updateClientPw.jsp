<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>비밀번호 수정</h1>
	<form action="<%=request.getContextPath()%>/UpdateClientPwController" method="post">
		<div>
			새 비밀번호:
			<input type="password" name="clientPw">
		</div>
		<button type="submit">비밀번호 바꾸기</button>
	</form>
</body>
</html> 