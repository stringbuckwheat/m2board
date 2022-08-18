<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>${map.boardNo}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${map.title}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${map.writer}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${map.content}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${map.createDate}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${map.read}</td>
		</tr>
	</table>
	<a href="${pageContext.request.contextPath}/niceController?boardNo=${map.boardNo}">좋아요 ${map.nice}</a>
</body>


</html>