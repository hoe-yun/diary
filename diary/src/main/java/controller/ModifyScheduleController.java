package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDao;
import vo.Schedule;


@WebServlet("/schedule/modifySchedule")
public class ModifyScheduleController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		int scheduleNo = Integer.parseInt(request.getParameter("scheduleNo"));
		int targetY = Integer.parseInt(request.getParameter("targetY"));
		int targetM = Integer.parseInt(request.getParameter("targetM"));
		int targetD = Integer.parseInt(request.getParameter("targetD"));
		
		ScheduleDao scheduleDao = new ScheduleDao();
		List<Schedule> list = scheduleDao.selectScheduleOne(scheduleNo);
		
		request.setAttribute("list", list);
		request.setAttribute("targetY", targetY);
		request.setAttribute("targetM", targetM);
		request.setAttribute("targetD", targetD);
		request.getRequestDispatcher("/WEB-INF/view/schedule/modifySchedule.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			//로그인 되어 있지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		int scheduleNo = Integer.parseInt(request.getParameter("scheduleNo"));
		String scheduleDate = request.getParameter("scheduleDate");
		String scheduleMemo = request.getParameter("scheduleMemo");
		String scheduleEmoji = request.getParameter("scheduleEmoji");
		int targetY = Integer.parseInt(request.getParameter("targetY"));
		int targetM = Integer.parseInt(request.getParameter("targetM"));
		int targetD = Integer.parseInt(request.getParameter("targetD"));
		
		ScheduleDao scheduleDao = new ScheduleDao();
		scheduleDao.modifySchedule(scheduleNo, scheduleDate, scheduleMemo, scheduleEmoji);
		
		response.sendRedirect(request.getContextPath()+"/schedule/scheduleByDay?targetY="+targetY+"&targetM="+targetM+"&targetD="+targetD);
	}

}
