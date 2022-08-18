<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>INSERT MEMBER</h1>
		<form method="post" action="${pageContext.request.contextPath}/signUpController">
			<table class="table table-hover table-striped">
				<tr>
					<td>id</td>
					<td><input type="text" name="id"></td>
				</tr>
				<tr>
					<td>pw</td>
					<td><input type="password" name="pw"></td>
				</tr>
				<tr>
					<td>name</td>
					<td><input type="text" name="name"></td>
				</tr>
			</table>
			<button type="submit">회원가입</button>
		</form>
	</div>
</body>
</html>