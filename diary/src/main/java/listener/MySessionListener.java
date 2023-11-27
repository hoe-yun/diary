package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dao.CounterDao;
import vo.Counter;


@WebListener
public class MySessionListener implements HttpSessionListener {

    //view에서 application.getAttribute("currentCnt")를 호출하면 현재 접속자 수 출력가능
    public void sessionCreated(HttpSessionEvent se)  { 
        // 현재 접속자
    	int n = (Integer)(se.getSession().getServletContext().getAttribute("currentCnt"));
    	se.getSession().getServletContext().setAttribute("currentCnt", n+1);
    	//오늘 날짜의 누적 접속자
    	CounterDao counterDao = new CounterDao();
    	Counter counter = counterDao.selectCntByToday();
    	if(counter == null) {	// 오늘 첫 접속
    		counterDao.insertCnt();
    	}else {	// 이미 접속자가 있었음
    		counterDao.updateCnt();
    	}
    }

	
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // -1
    	int n = (Integer)(se.getSession().getServletContext().getAttribute("currentCnt"));
    	se.getSession().getServletContext().setAttribute("currentCnt", n-1);
    }
	
}
