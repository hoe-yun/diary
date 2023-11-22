<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<h1>로그인</h1>
<a href="${pageContext.request.contextPath}/member/addMember" class="btn btn-outline-dark">회원가입</a>
<form id="loginForm" method="post" action="${pageContext.request.contextPath}/member/loginMember">
	<table border="1">
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