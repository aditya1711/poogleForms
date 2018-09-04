package poogleForms.listeners;

import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import poogleForms.DAO.AnswersDAO;
import poogleForms.DAO.ClientsDAO;
import poogleForms.DAO.FormDAO;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener, ServletContextAttributeListener {

    private   String DB_URL = null;
	private   String DB_User = null;
	private   String DB_Password = null;

	/**
     * Default constructor. 
     */
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	DB_URL =  arg0.getServletContext().getInitParameter("DB_URL");
    	DB_User =  arg0.getServletContext().getInitParameter("DB_User");
    	DB_Password =  arg0.getServletContext().getInitParameter("DB_Password");
    	
    	try {
			FormDAO formDAO = FormDAO.getFormDAO(DB_URL, DB_User, DB_Password);
			ClientsDAO clientsDAO = ClientsDAO.getClientDAO(DB_URL, DB_User, DB_Password);
			AnswersDAO answersDAO = AnswersDAO.getAnswersDAO(DB_URL, DB_User, DB_Password);
			ServletContext ctx = arg0.getServletContext();
			
			ctx.setAttribute("formDAO", formDAO);
			ctx.setAttribute("clientsDAO", clientsDAO);
			ctx.setAttribute("answersDAO", answersDAO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
