package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDao;
import vo.Comment;
import vo.Member;

@WebServlet("/comment/modifyComment")
public class ModifyCommentController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		
		CommentDao commentDao = new CommentDao();
		Comment comment = commentDao.selectComment(commentNo);
		
		request.setAttribute("comment", comment);
		
		request.getRequestDispatcher("/WEB-INF/view/comment/modifyComment.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		//modifyComment.jsp에서 보낸 정보 출력
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String commentContent = request.getParameter("commentContent");
		String isSecret = request.getParameter("isSecret");
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		String loginId = loginMember.getMemberId();
		
		Comment comment = new Comment();
		comment.setCommentNo(commentNo);
		comment.setNoticeNo(noticeNo);
		comment.setCommentContent(commentContent);
		comment.setIsSecret(isSecret);
		comment.setMemberId(loginId);
		
		CommentDao commentDao = new CommentDao();
		commentDao.updateComment(comment);
		
		response.sendRedirect(request.getContextPath()+"/notice/noticeOne?noticeNo="+noticeNo+"&currentPage=1");
	}

}
