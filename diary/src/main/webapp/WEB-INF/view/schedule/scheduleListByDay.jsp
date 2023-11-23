<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<div class="container"><br><br><br>
	<h1 align="center">${paramMemberId }님의 ${targetY }년 ${targetM }월 ${targetD }일의 스케쥴</h1><br><br><br>
	<table class="table table-boredered">
		<tr class="table-info" align="center">
			<th>스케줄 내용</th>
			<th></th>
		</tr>
		<c:forEach var="s" items="${list}">
			<tr align="center">
				<td>
					${s.scheduleMemo }
				</td>
				<td>
					<a href="" class="btn btn-outline-dark" style="flout:right">수정</a>
					<a href="" class="btn btn-outline-danger" style="flout:right">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br><br>
	<div align="center">
		<form action="" method="" id="">
			<textarea id="" style="width:500px; height:25px;"></textarea>
			<button class="btn btn-outline-dark">추가</button>
		</form>
	</div>
</div>
</body>
</html>