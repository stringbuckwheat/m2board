<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>
<body>
	<div>
		<h1>LOGIN</h1>
		<form method="post" action="${pageContext.request.contextPath}/loginController">
			<table class="table table-hover table-striped">
				<tr>
					<td>id</td>
					<td><input type="text" name="id"></td>
				</tr>
				<tr>
					<td>pw</td>
					<td><input type="password" name="pw"></td>
				</tr>
			</table>
			<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>