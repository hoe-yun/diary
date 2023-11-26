package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NoticeDao;
import vo.Member;
import vo.Notice;

@WebServlet("/notice/addNotice")
public class AddNoticeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/view/notice/addNotice.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		// addNotice.jsp에서 보낸 정보 받기
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		String noticePw = request.getParameter("noticePw");
		
		Member paramMember = (Member)session.getAttribute("loginMember");
		String loginId = paramMember.getMemberId();
		
		NoticeDao noticeDao = new NoticeDao();
		Notice paramNotice = new Notice();
		paramNotice.setNoticeTitle(noticeTitle);
		paramNotice.setNoticeContent(noticeContent);
		paramNotice.setMemberId(loginId);
		paramNotice.setNoticePw(noticePw);
		
		noticeDao.insertNotice(paramNotice);
		
		response.sendRedirect(request.getContextPath()+"/member/memberHome");
	}

}
