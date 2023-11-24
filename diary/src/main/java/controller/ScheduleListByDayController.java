package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDao;
import vo.Member;
import vo.Schedule;

@WebServlet("/schedule/scheduleByDay")
public class ScheduleListByDayController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		//세션에서 id뽑기
		Member paramMember = (Member)session.getAttribute("loginMember");
		String paramMemberId = paramMember.getMemberId();
		
		//타켓 연,월,날짜 받기
		int targetY = Integer.parseInt(request.getParameter("targetY"));
		int targetM = Integer.parseInt(request.getParameter("targetM"));
		int targetD = Integer.parseInt(request.getParameter("targetD"));
		//타켓 연 월 날짜를 yyyy-MM-dd 형식(LocalDate 타입)으로 합침
		LocalDate date = LocalDate.of(targetY, targetM, targetD);
		//LocalDate 타입을 String으로
		String scheduleDate = date.toString();
		
		ScheduleDao scheduleDao = new ScheduleDao();
		List<Schedule> list = scheduleDao.selectScheduleByDay(paramMemberId, scheduleDate);
		System.out.println(paramMemberId+" <-id, "+scheduleDate+" <-date, "+list.size()+" <-size");
		
		request.setAttribute("list", list);
		request.setAttribute("targetY", targetY);
		request.setAttribute("targetM", targetM);
		request.setAttribute("targetD", targetD);
		request.setAttribute("paramMemberId", paramMemberId);
		request.getRequestDispatcher("/WEB-INF/view/schedule/scheduleListByDay.jsp").forward(request, response);

	}
}
