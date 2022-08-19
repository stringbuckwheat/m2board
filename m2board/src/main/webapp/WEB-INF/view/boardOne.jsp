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
	<input type="hidden" name="boardNo" id="boardNo" value="${map.boardNo}">
	<input type="button" id="niceBtn" value="좋아요 ${map.nice}">
</body>

<script>
	$('#niceBtn').click(function(){
		$.ajax({
			url : '/m2board/niceController',
			type : 'get',
			data : {boardNo : $('#boardNo').val()},
			success: function(json){
				alert('좋아요 성공!');
				$('#niceBtn').val('좋아요 ' + json);
				console.log('좋아요 ' + json);
			}
		})
	})

</script>


</html>