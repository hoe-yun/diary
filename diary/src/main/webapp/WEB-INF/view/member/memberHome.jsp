<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<br>
	<div class="container">
		<div align="center">
			<div><h4> 접속자 : ${currentCnt }             누적 접속자 : ${CntSum }</h4></div><br>
			<table class="table table-bordered col-lg-12">
				<tr align="center" class="table table-info">
					<th class="col-lg-2">공지 번호</th>
					<th class="col-lg-8">공지 제목</th>
					<th class="col-lg-2">공지 날짜</th>
				</tr>
					<c:forEach var="n" items="${noticeList }">
						<tr align="center">
							<td>${n.getNoticeNo()}</td>
							<td>
								<a href="${pageContext.request.contextPath}/notice/noticeOne?noticeNo=${n.getNoticeNo()}">${n.getNoticeTitle()}</a>
							</td>
							<td>${n.getCreatedate()}</td>
						</tr>
					</c:forEach>
			</table>
		</div>
		<div align="center">
			<a href="${pageContext.request.contextPath}/member/memberHome?currentPage=
				<c:if test="${(currentPage-1)<1}">
					${currentPage}
				</c:if>
				<c:if test="${!((currentPage-1)<1)}">
					${currentPage-1}
				</c:if>
				" style="float:left" class="btn btn-outline-dark">이전 공지</a>
			<a href="" class="btn btn-outline-dark">${currentPage}</a>
			<c:if test="${loginLevel > 0 }">
				<a href="${pageContext.request.contextPath}/notice/addNotice" class="btn btn-outline-dark">공지추가</a>
			</c:if>
			<a href="${pageContext.request.contextPath}/member/memberHome?currentPage=
				<c:if test="${(currentPage+1)>lastPage}">
					${currentPage}
				</c:if>
				<c:if test="${!((currentPage+1)>lastPage)}">
					${currentPage+1}
				</c:if>
				" style="float:right" class="btn btn-outline-dark">다음 공지</a>
		</div><br>
		<div align="center">
			<h1>Home</h1><br>
			<h1>ID : ${loginMember.memberId}</h1><br>
		</div>
		<div align="center">
			<a href="${pageContext.request.contextPath}/member/logoutMember" class="btn btn-outline-dark">로그아웃</a>
			<a href="${pageContext.request.contextPath}/member/modifyMember" class="btn btn-outline-dark">비밀번호수정</a>
			<a href="${pageContext.request.contextPath}/member/removeMember" class="btn btn-outline-dark">회원탈퇴</a>
		</div>
		<br><br>
	<!-- 달력 -->
		<div>
			<h1 align="center">${targetY}년 ${targetM+1}월 달력</h1>
			<div>
				<!-- 만약 href속성값 매개값이 많으면 c:url jstl을 사용하면 가독성 높일 수 있다 -->
				<c:url var = "nextUrl" value="/member/memberHome">
					<c:param name="targetY" value="${targetY}"></c:param>
					<c:param name="targetM" value="${targetM+1}"></c:param>	
				</c:url>
				<c:url var = "preUrl" value="/member/memberHome">
					<c:param name="targetY" value="${targetY}"></c:param>
					<c:param name="targetM" value="${targetM-1}"></c:param>	
				</c:url>
				
				<a href="${preUrl}" class="btn btn-outline-dark">이전달</a>
				<a href="${nextUrl}" class="btn btn-outline-dark" style="float:right">다음달</a>
			</div>
			<br>
			<table class="table table-bordered col-lg-12">
				<tr align="center" class="table table-info">
					<th class="col-lg-1" style="color:red">SUN</th>
					<th class="col-lg-1">MON</th>
					<th class="col-lg-1">TUE</th>
					<th class="col-lg-1">WED</th>
					<th class="col-lg-1">THU</th>
					<th class="col-lg-1">FRI</th>
					<th class="col-lg-1">SAT</th>
				</tr>
				<tr>
					<c:forEach var="i" begin="1" end="${totalTd}" step="1">
						<c:set var="d" value="${i - beginBlank }"></c:set>
						<c:if test="${i % 7 == 1}">
							<td style="color:red">
						</c:if>
						<c:if test="${!(i % 7 == 1)}">
							<td>
						</c:if>
							<c:if test="${d < 1 || d > lastD}">
								&nbsp;
							</c:if>
							<c:if test="${!(d < 1 || d > lastD)}">
								${d}
								<a href="${pageContext.request.contextPath}/schedule/scheduleByDay?targetY=${targetY}&targetM=${targetM+1}&targetD=${d}" style="float:right">자세히</a><br><br>
								<span style="color:black;">스케줄 :</span> 
								<c:forEach var="m" items="${list }">
								<div> 
									<c:if test="${m.scheduleDay == d }">
										<div align="center" class="d-grid gap-1">
											<button class="btn btn-outline-success btn-block" data-bs-toggle="tooltip" data-bs-placement="top" title="${m.memo }">
												${m.cnt }
											</button>
										</div>
									</c:if>
								</div>
								</c:forEach>
							</c:if>
							<c:if test="${i<totalTd && i%7==0}">
								</tr><tr>
							</c:if>
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>