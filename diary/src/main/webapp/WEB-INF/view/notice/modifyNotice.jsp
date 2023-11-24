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
	<div class="container" align="center">
		<br><br><div><h1>공지 수정</h1></div><br><br>
		<form action="${pageContext.request.contextPath}/notice/modifyNotice" method="post" id="">
			<table class="table col-lg-12">
				<tr align="center">
					<th class="table-info"><br><br><br>공지 번호<br><br><br><br></th>
					<td><br><br><br><input style="width:250px" type="text" name="noticeNo" value="${paramNotice.getNoticeNo() }" readonly="readonly"></td>
				</tr>
				<tr align="center">
					<th class="table-info"><br><br><br>공지 제목<br><br><br><br></th>
					<td><br><br><br><input style="width:250px" type="text" name="noticeTitle" value="${paramNotice.getNoticeTitle() }"></td>
				</tr>
				<tr align="center">
					<th class="table-info"><br><br><br>공지 내용<br><br><br><br></th>
					<td><br><textarea rows="5" cols="30" name="noticeContent">${paramNotice.getNoticeContent() }</textarea></td>
				</tr>
				<tr align="center">
					<th class="table-info"><br><br><br>비밀 번호<br><br><br><br></th>
					<td><br><br><br><input style="width:250px" type="password" name="noticePw"></td>
				</tr>
			</table>
			<button id="" class="btn btn-outline-dark" style="float:right">수정</button>
		</form>
		<a href="" class="btn btn-outline-dark" style="float:left">뒤로</a>
	</div>
</body>
</html>