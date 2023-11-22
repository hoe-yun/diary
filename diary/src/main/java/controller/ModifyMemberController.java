package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/member/modifyMember")
public class ModifyMemberController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/member/modifyMemberPw.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		Member loginMember = (Member)session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		String memberPw = request.getParameter("memberPw");
		String newMemberPw = request.getParameter("newMemberPw");
		
		MemberDao memberDao = new MemberDao();
		int row = memberDao.modifyPwMember(memberId, newMemberPw, memberPw);
		System.out.println(row+" <--row");
		if(row != 0) {
			System.out.println("비밀번호 수정 성공");
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}else {
			System.out.println("비밀번호 수정 실패");
			response.sendRedirect(request.getContextPath()+"/member/modifyMember");
			return;
		}
	}

}