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

@WebServlet("/notice/removeNotice")
public class RemoveNoticeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		request.setAttribute("noticeNo", noticeNo);
		request.getRequestDispatcher("/WEB-INF/view/notice/removeNotice.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		// removeNotice.jsp 에서 보낸 정보 받기
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String noticePw = request.getParameter("noticePw");
		
		Member paramMember = (Member)session.getAttribute("loginMember");
		String loginId = paramMember.getMemberId();
		
		Notice paramNotice = new Notice();
		paramNotice.setNoticeNo(noticeNo);
		paramNotice.setNoticePw(noticePw);
		paramNotice.setMemberId(loginId);
		
		NoticeDao noticeDao = new NoticeDao();
		noticeDao.deleteNotice(paramNotice);
		
		response.sendRedirect(request.getContextPath()+"/member/memberHome");
	}

}
