package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDao;
import vo.Member;


@WebServlet("/schedule/addSchedule")
public class AddScheduleController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		int targetY = Integer.parseInt(request.getParameter("year"));
		int targetM = Integer.parseInt(request.getParameter("month"));
		int targetD = Integer.parseInt(request.getParameter("day"));
		String scheduleMemo = request.getParameter("memo");
		String scheduleEmoji = request.getParameter("emoji");
		//타켓 연 월 날짜를 yyyy-MM-dd 형식(LocalDate 타입)으로 합침
		LocalDate date = LocalDate.of(targetY, targetM, targetD);
		//LocalDate 타입을 String으로
		String scheduleDate = date.toString();
		//세션에서 id뽑기
		Member paramMember = (Member)session.getAttribute("loginMember");
		String paramMemberId = paramMember.getMemberId();
		
		ScheduleDao scheduleDao = new ScheduleDao();
		scheduleDao.addSchedule(paramMemberId, scheduleDate, scheduleMemo, scheduleEmoji);
		
		response.sendRedirect(request.getContextPath()+"/schedule/scheduleByDay?targetY="+targetY+"&targetM="+targetM+"&targetD="+targetD);
	}

}
