package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class MyContextListener implements ServletContextListener {

    
    public void contextDestroyed(ServletContextEvent sce)  { 
         
    }

	
    public void contextInitialized(ServletContextEvent sce)  { 
    	//application.setAttribute()
         sce.getServletContext().setAttribute("currentCnt", 0);
         System.out.println("MyContextListener currentCnt ->"+sce.getServletContext().getAttribute("currentCnt"));
    }
	
}
