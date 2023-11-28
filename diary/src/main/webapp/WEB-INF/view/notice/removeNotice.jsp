<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container"> 
	<br><br><div align="center"><h1>공지 삭제</h1></div><br><br>
	<form action="${pageContext.request.contextPath}/notice/removeNotice" method="post" id="">
		<table class="table col-lg-12">
			<tr align="center">
				<th class="table-info"><br>공지 번호<br><br></th>
				<td><br><input type="text" name="noticeNo" value="${noticeNo}" readonly="readonly"></td>
			</tr>
			<tr align="center">
				<th class="table-info"><br>비밀 번호<br><br></th>
				<td><br><input type="password" name="noticePw" id="noticePw"></td>
			</tr>
		</table>
		<button type="submit" id="" class="btn btn-outline-danger" style="float:right">삭제</button>
	</form>
	<a href="${pageContext.request.contextPath}/notice/noticeOne?noticeNo=${noticeNo }&currentPage=1" class="btn btn-outline-dark" style="float:left">뒤로</a>
</div>
</body>
<script type="text/javascript">
	$('#noticePw').blur(function(){
		if($('#noticePw').val().length < 0){
			alert('비밀번호를 입력하세요');
			
		}
	})
</script>
</html>