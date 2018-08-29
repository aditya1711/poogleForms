package poogleForms.controller.general;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poogleForms.model.clients.ClientTypes;
import poogleForms.model.clients.Level2Clients;
import poogleForms.model.clients.LoginCredentials;

/**
 * Servlet implementation class Dasboard
 */
@WebServlet("/Dashboard")
public class Dasboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dasboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Level2Clients l2c =new Level2Clients();
		l2c.setFirstName("hasan");
		l2c.setLastName("faraz");
		l2c.setLoginCredentials(new LoginCredentials("hasan123", "hasan123", ClientTypes.LEVEL2));
		l2c.setAnswersIDs(new HashSet<Long>());
		l2c.setFormsCreatedIDs(new HashSet<Long>());
		
		
		HttpSession session = (HttpSession)request.getSession();
		
		session.setAttribute("client", l2c);
		
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
