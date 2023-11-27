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

@WebServlet("/comment/addComment")
public class AddCommentController extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		//noticeOne.jsp에서 보낸 정보 받기
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String commentContent = request.getParameter("commentContent");
		String isSecret = request.getParameter("isSecret");
		
		System.out.println(isSecret + " <--- 비밀글");
		//세션의 아이디 
		Member loginMember = (Member)session.getAttribute("loginMember");
		String loginId = loginMember.getMemberId();
		
		Comment comment = new Comment();
		comment.setNoticeNo(noticeNo);
		comment.setCommentContent(commentContent);
		comment.setIsSecret(isSecret);
		comment.setMemberId(loginId);
		System.out.println(comment.getIsSecret()+"<---비밀글?");
		CommentDao commentDao = new CommentDao();
		commentDao.insertComment(comment);
		
		response.sendRedirect(request.getContextPath()+"/notice/noticeOne?noticeNo="+noticeNo+"&currentPage=1");
	}

}
