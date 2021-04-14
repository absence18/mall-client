<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertClientForm</title>
</head>
<body>
	<h1>회원가입</h1><!-- 동일한 주소를 입력해도 doget과 dopost를 나뉘어서 사용해서 같은 주소도 가능 -->
	<form method="post" action="<%=request.getContextPath()%>/InsertClientController">
		<div>
			Mail:
			<input type="text" name="clientMail">
		</div>
		<div>
			PW:
			<input type="password" name="clientPw">
		</div>
		<button type="submit">회원가입</button>
	</form>

</body>
</html> 