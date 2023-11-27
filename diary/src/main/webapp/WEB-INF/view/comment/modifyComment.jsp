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
	<br><br><div><h1>코멘트 수정</h1></div><br><br>
	<form action="${pageContext.request.contextPath}/comment/modifyComment" method="post" id="">
		<table class="table col-lg-12">
			<tr align="center">
				<th class="table-info"><br><br><br>코멘트<br><br><br><br></th>
				<td><br><br><br><input style="width:250px" type="text" name="commentContent" value="${comment.getCommentContent() }"></td>
			</tr>
			<tr align="center">
				<th class="table-info"><br><br><br>비밀글 여부<br><br><br><br></th>
				<td>
					<br><br><br><select name="isSecret">
							<option value="false">공개글
							<option value="true">비밀글
					</select>
				</td>
			</tr>
		</table>
		<input type="hidden" name="commentNo" value="${comment.getCommentNo() }">
		<input type="hidden" name="noticeNo" value="${noticeNo }">
		<button id="" class="btn btn-outline-dark" style="float:right">수정</button>
	</form>
	<a href="${pageContext.request.contextPath}/notice/noticeOne?noticeNo=${noticeNo }&currentPage=1" class="btn btn-outline-dark" style="float:left">뒤로</a>
</div>
</body>
</html>