<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${targetY}년 ${targetM }월 ${targetD }일 스케줄 수정</h1>
<form action="${pageContext.request.contextPath}/schedule/modifySchedule" method="post" id="">
	<table border="1">
		<c:forEach var="s" items="${list }">
			<tr>
				<th>날짜</th>
				<td><input type="date" name="scheduleDate" value="${s.scheduleDate }"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="scheduleMemo" rows="1" cols="30">${s.scheduleMemo }</textarea></td>
			</tr>
			<tr>
				<th>이모지</th>
				<td><input type="text" name="scheduleEmoji" value="${s.scheduleEmoji }"></td>
			</tr>
			<input type="hidden" name="scheduleNo" value="${s.scheduleNo }">
		</c:forEach>
	</table>
	<button type="submit">수정</button>
	<a href="${pageContext.request.contextPath}/schedule/scheduleByDay?targetY=${targetY}&targetM=${targetM}&targetD=${targetD}">
		뒤로
	</a>
	<input type="hidden" name="targetY" value="${targetY}">
	<input type="hidden" name="targetM" value="${targetM}">
	<input type="hidden" name="targetD" value="${targetD}">
</form>
</body>
</html>