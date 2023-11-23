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
<h1>회원 탈퇴</h1>
<form id="formRemove" method="post" action="${pageContext.request.contextPath}/member/removeMember">
	<table class="table">
		<tr>
			<th>비밀번호 확인</th>
			<td><input type="password" id="memberPw" name="memberPw"></td>
		</tr>
	</table>
	<button id="formBtn" class="btn btn-outline-dark">탈퇴</button>
	<a href="${pageContext.request.contextPath}/member/memberHome" class="btn btn-outline-dark">뒤로</a>
</form>
</body>
<script type="text/javascript">
	// 비밀번호 유효성 검사
	$('#formBtn').click(function(){
		if($('#memberPw').val().length < 1){
			alert('비밀번호를 입력하세요')
		}else{
			$('#formRemove').submit()
		}
	});
</script>
</html>