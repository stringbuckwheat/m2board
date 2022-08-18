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
			<td>${board.boardNo}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${board.title}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.writer}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${board.content}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${board.createDate}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${board.read}</td>
		</tr>
	</table>
	<button type="button" id="likeBtn">좋아요 ${board.nice}</button>
</body>

<script>

$('#likeBtn').click(function() {
	
})

</script>

</html>