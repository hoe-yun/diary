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
<br><br>
<div class="container">
	<div align="center">
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
			<a href="${pageContext.request.contextPath}/notice/noticeOne?noticeNo=${paramNotice.getNoticeNo() }&currentPage=
				<c:if test="${(currentPage-1)<1}">
					${currentPage}
				</c:if>
				<c:if test="${!((currentPage-1)<1)}">
					${currentPage-1}
				</c:if>
				" style="float:left" class="btn btn-outline-dark">이전 공지</a>
			<a href="" class="btn btn-outline-dark">${currentPage}</a>
			<a href="${pageContext.request.contextPath}/notice/noticeOne?noticeNo=${paramNotice.getNoticeNo() }&currentPage=
				<c:if test="${(currentPage+1)>lastPage}">
					${currentPage}
				</c:if>
				<c:if test="${!((currentPage+1)>lastPage)}">
					${currentPage+1}
				</c:if>
				" style="float:right" class="btn btn-outline-dark">다음 공지</a>
		</div><br><br>
	<fieldset class="border" style="background-color:#D4F4FA">
		<legend><div align="center">
			<br><h1>${paramNotice.getNoticeTitle() }</h1>
		</div></legend>
		<div align="right">
			<h5>작성일 : ${paramNotice.getCreatedate() }</h5>
		</div>
	</fieldset><br><br><br>
	<div align="center">
		<h3>${paramNotice.getNoticeContent() }</h3>
	</div><br><br><br>
	<fieldset class="border" style="background-color:#D4F4FA">
		<br>
		<div>
			<a href="${pageContext.request.contextPath}/member/memberHome" class="btn btn-outline-dark btn-light">홈으로</a>
			<c:if test="${loginLevel != 0 }">
				<a href="${pageContext.request.contextPath}/notice/modifyNotice?noticeNo=${paramNotice.getNoticeNo()}" class="btn btn-outline-dark btn-light">수정</a> 
				<a href="${pageContext.request.contextPath}/notice/removeNotice?noticeNo=${paramNotice.getNoticeNo()}" class="btn btn-outline-danger btn-light">삭제</a> 
			</c:if>
		</div>
		<div align="right">
			<h5>작성자 : ${paramNotice.getMemberId() }</h5>
		</div>
	</fieldset>
	<div align="center">
		<br><h3>코멘트</h3><br>
		<table class="table col-lg-12">
			<c:forEach var="c" items="${commentList }">
			<tr align="center">
				<td class="col-lg-8">${c.getCommentContent() }</td>	
				<td class="col-lg-1">${c.getMemberId() }</td>
				<td class="col-lg-1">${c.getCreatedate() }</td>
				<td class="col-lg-2">
					<a href="${pageContext.request.contextPath}/comment/modifyComment?commentNo=${c.getCommentNo()}&noticeNo=${c.getNoticeNo()}" class="btn btn-outline-dark btn-light">수정</a> 
					<a href="${pageContext.request.contextPath}/comment/removeComment?commentNo=${c.getCommentNo()}&noticeNo=${c.getNoticeNo()}" class="btn btn-outline-danger btn-light">삭제</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div><br><br><br>
	<div align="center">	
		<form action="${pageContext.request.contextPath}/comment/addComment" method="post" id="">
			<input type="hidden" name="noticeNo" value="${paramNotice.getNoticeNo() }">
			<table>
				<tr>
					<td>
						<textarea id="" name="commentContent" rows="1" cols="50"></textarea>
						&nbsp;&nbsp;&nbsp;
						<select name="isSecret">
							<option value="false">공개글
							<option value="true">비밀글
						</select>
						&nbsp;&nbsp;&nbsp;
						<button class="btn btn-outline-dark btn-light" style="float:right">추가</button>
					</td>
				</tr>
			</table>
			
		</form>
	</div>
</div>
</body>
</html>