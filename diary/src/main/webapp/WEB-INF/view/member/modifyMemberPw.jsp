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
<h1>비밀번호 변경</h1>
<form id="formPw" method="post" action="${pageContext.request.contextPath}/member/modifyMember">
	<table border="1">
		<tr>
			<th>현재 비밀번호</th>
			<td><input type="password" id="memberPw" name="memberPw"></td>
		</tr>
		<tr>
			<th>새로운 비밀번호</th>
			<td><input type="password" id="newMemberPw" name="newMemberPw"></td>
		</tr>
	</table>
	<button id="formBtn" class="btn btn-outline-dark">비밀번호 변경</button>
</form>
<form action="${pageContext.request.contextPath}/member/memberHome"><button type="submit" class="btn btn-outline-dark">뒤로</button></form>
</body>

<script type="text/javascript">
	// 비밀번호 유효성 검사
	$('#memberPw').blur(function(){
		if($('#memberPw').val().length < 1){
			$('#memberPw').focus();
		}else{
			$('#newMemberPw').focus();
		}
	});
	// 새로운 비밀번호 유효성 검사
	$('#newMemberPw').blur(function(){
		if($('#newMemberPw').val().length < 1){
			$('#newMemberPw').focus();
		}else if($('#memberPw').val() == $('#newMemberPw').val()){
			alert('현재와 다른 비밀번호를 입력해주세요')
			$('#newMemberPw').focus();
		}else{
			$('#formBtn').focus();
		}
	});
</script>
</html>