package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NoticeDao;
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
	}

}
