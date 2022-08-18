<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<h3>Index</h3>
	<h3>${loginMember.name}(${loginMember.id})님, 반갑습니다</h3>
	<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
</body>
</html>