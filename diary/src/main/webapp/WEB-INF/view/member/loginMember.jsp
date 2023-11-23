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
<h1>로그인</h1>
<form id="loginForm" method="post" action="${pageContext.request.contextPath}/member/loginMember">
	<table class="table col-lg-6">
		<tr>
			<th>아이디</th>
			<td><input type="text" id="memberId" name="memberId"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" id="memberPw" name="memberPw"></td>
		</tr>
	</table>
	<button id="loginBtn" class="btn btn-outline-dark">로그인</button>
`	<a href="${pageContext.request.contextPath}/member/addMember" class="btn btn-outline-dark" style="float:right">회원가입</a>
</form>
</body>

<script type="text/javascript">
	$('#memberId').blur(function(){
		if($('#memberId').val().length < 1){
			$('#memberId').focus();
		}else{
			$('#memberPw').focus();
		}
	});
	
	$('#memberPw').blur(function(){
		if($('#memberPw').val().length < 1){
			$('#memberPw').focus();
		}else{
			$('loginBtn').focus();
		}
	});
</script>
</html>