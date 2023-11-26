<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div align="center"><h1>공지 추가</h1></div>
	<form action="${pageContext.request.contextPath}/notice/addNotice" method="post">
		<table border="1">
			<tr>
				<th>공지 제목</th>
				<td><input type="text" name="noticeTitle"></td>
			</tr>
			<tr>
				<th>공지 내용</th>
				<td><textarea name="noticeContent"></textarea></td>
			</tr>
			<tr>
				<th>비밀 번호</th>
				<td>
					<input type="password" name="noticePw">
				</td>
			</tr>
		</table>
		<button type="submit" class="">추가</button>
 	</form> 
 	<a href="${pageContext.request.contextPath}/member/memberHome" class="">뒤로</a>
</div>
</body>
</html>