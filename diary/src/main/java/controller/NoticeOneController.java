package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDao;
import dao.NoticeDao;
import vo.Comment;
import vo.Member;
import vo.Notice;

@WebServlet("/notice/noticeOne")
public class NoticeOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		// 공지목록 출력 모델
		int currentPage = 1;
		if(request.getParameter("currentPage") != null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 3;
		int beginRow = (currentPage-1)*rowPerPage;
		
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		
		NoticeDao noticeDao = new NoticeDao();
		List<Notice> noticeList = noticeDao.selectNoticeList(map);
		
		int totalNotice = noticeDao.noticeCNT();
		int lastPage = totalNotice / rowPerPage;
		if((totalNotice % rowPerPage) != 0 ) {
			lastPage = lastPage + 1;
		}
		//공지 내용 출력 모델
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		Notice paramNotice = noticeDao.noticeOne(noticeNo);
		
		//멤버 세션의 레벨 출력
		Member loginMember = (Member)session.getAttribute("loginMember");
		int loginLevel = loginMember.getMemberLevel();
		String loginId = loginMember.getMemberId();
		System.out.println(loginLevel + " <---level @@");
		System.out.println(loginId + " <---loginId @@");
		
		//코멘트 출력 모델
		int currentCommentPage = 1;
		if(request.getParameter("currentCommentPage") != null){
			currentCommentPage = Integer.parseInt(request.getParameter("currentCommentPage"));
		}
		int rowPerCommentPage = 3;
		int beginCommentRow = (currentCommentPage-1)*rowPerCommentPage;
		CommentDao commentDao = new CommentDao();
		int totalComment = commentDao.commentCnt(noticeNo);
		int lastCommentPage = totalComment / rowPerCommentPage;
		if((totalComment % rowPerCommentPage) != 0 ) {
			lastCommentPage = lastCommentPage + 1;
		}
		Map<String, Object> commentMap = new HashMap<>();
		commentMap.put("beginCommentRow", beginCommentRow);
		commentMap.put("rowPerCommentPage", rowPerCommentPage);
		commentMap.put("noticeNo", noticeNo);
		List<Comment> commentList = commentDao.selectCommentList(commentMap);
		
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("paramNotice", paramNotice);
		request.setAttribute("loginLevel", loginLevel);
		request.setAttribute("loginId", loginId);
		request.setAttribute("currentCommentPage", currentCommentPage);
		request.setAttribute("lastCommentPage", lastCommentPage);
		request.setAttribute("commentList", commentList);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/noticeOne.jsp").forward(request, response);
		
	}

}
