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

@WebServlet("/notice/modifyNotice")
public class ModifyNoticeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		NoticeDao noticeDao = new NoticeDao();
		Notice paramNotice = noticeDao.noticeOne(noticeNo);
		
		request.setAttribute("paramNotice", paramNotice);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/modifyNotice.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		// modifyNotice.jsp에서 받는 값 출력
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		String noticePw = request.getParameter("noticePw");
		System.out.println(noticeTitle+" <-- 왜 깨지냐");
		// session에서 memberId 출력
		Member loginMember = (Member)session.getAttribute("loginMember");
		String loginId = loginMember.getMemberId();
		
		//NoticeDao에서 update메소드 호출 후 값 삽입
		NoticeDao noticeDao = new NoticeDao();
		Notice paramNotice = new Notice();
		paramNotice.setNoticeNo(noticeNo);
		paramNotice.setNoticeTitle(noticeTitle);
		paramNotice.setNoticeContent(noticeContent);
		paramNotice.setNoticePw(noticePw);
		paramNotice.setMemberId(loginId);
		int row = noticeDao.updateNotice(paramNotice);
		if(row == 0) {
			System.out.println("수정실패");
		}else {
			System.out.println("수정성공");
		}
		response.sendRedirect(request.getContextPath()+"/notice/noticeOne?noticeNo="+noticeNo+"&currentPage=1");
	}

}
