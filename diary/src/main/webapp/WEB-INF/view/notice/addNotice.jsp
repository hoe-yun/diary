<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
	<br><br><div align="center"><h1>공지 추가</h1></div><br><br>
	<form action="${pageContext.request.contextPath}/notice/addNotice" method="post">
		<table class="table col-lg-12">
			<tr align="center">
				<th class="table-info"><br>공지 제목<br><br></th>
				<td><br><input type="text" name="noticeTitle"></td>
			</tr>
			<tr align="center">
				<th class="table-info"><br>공지 내용<br><br></th>
				<td><textarea rows="3" cols="22" name="noticeContent"></textarea></td>
			</tr>
			<tr align="center">
				<th class="table-info"><br>비밀 번호<br><br></th>
				<td>
					<br><input type="password" name="noticePw">
				</td>
			</tr>
		</table>
		<button type="submit" class="btn btn-outline-dark" style="float:right">추가</button>
 	</form> 
 	<a href="${pageContext.request.contextPath}/member/memberHome" class="btn btn-outline-dark" >뒤로</a>
</div>
</body>
</html>