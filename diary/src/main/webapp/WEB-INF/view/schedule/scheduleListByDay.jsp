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
	<a href="${pageContext.request.contextPath}/member/memberHome" class="btn btn-outline-dark" style="float:right">뒤로</a><br><br>
	<table class="table table-boredered">
		<tr class="table-info" align="center">
			<th>스케줄 내용</th>
			<th></th>
		</tr>
		<c:forEach var="s" items="${list}">
			<tr align="center">
				<td>
					${s.scheduleMemo }
					<input type="hidden" name="scheduleNo" value="${s.scheduleNo }">
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/schedule/modifySchedule?scheduleNo=${s.scheduleNo }&targetY=${targetY}&targetM=${targetM}&targetD=${targetD}" class="btn btn-outline-dark">수정</a>
					<a href="${pageContext.request.contextPath}/schedule/removeSchedule?scheduleNo=${s.scheduleNo }&targetY=${targetY}&targetM=${targetM}&targetD=${targetD}" class="btn btn-outline-danger">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<div align="center">
		<fieldset class="border" style="background-color:#D4F4FA">
			<legend class="float-none w-auto p-2" align="center">스케줄 추가</legend>
				<form action="${pageContext.request.contextPath}/schedule/addSchedule" method="post" id="">
					<input type="hidden" name="year" value="${targetY }">
					<input type="hidden" name="month" value="${targetM }">
					<input type="hidden" name="day" value="${targetD }">
					<table>
						<tr>
							<td>
								<textarea id="" name="memo" rows="1" cols="50"></textarea>
							</td>
						</tr>
						<tr>
							<td><br>
								<input type="radio" name="emoji" value="&#1F4DD;"><span>&#128110;</span>
								<input type="radio" name="emoji" value="&#128186;">&#128186;
								<input type="radio" name="emoji" value="&#127981;">&#127981;
								<button class="btn btn-outline-dark btn-light" style="float:right;">추가</button>
							</td>
						</tr>
					</table>
				</form><br>
		</fieldset>
	</div>
</div>
</body>
</html>