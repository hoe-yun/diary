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
	<div align="center"><h1>공지 삭제</h1></div>
	<form action="${pageContext.request.contextPath}/notice/removeNotice" method="post" id="">
		<table>
			<tr>
				<th>공지 번호</th>
				<td><input type="text" name="noticeNo" value="${noticeNo }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>비밀 번호</th>
				<td><input type="password" name="noticePw"></td>
			</tr>
		</table>
		<button type="submit" id="">삭제</button>
	</form>
	<a href="${pageContext.request.contextPath}/notice/noticeOne?noticeNo=${paramNotice.getNoticeNo() }&currentPage=1" class="btn btn-outline-dark" style="float:left">뒤로</a>
</div>
</body>
</html>