package poogleForms.controller.general;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poogleForms.DAO.ClientsDAO;
import poogleForms.model.clients.Client;
import poogleForms.model.clients.Level1Clients;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ClientsDAO clientsDAO;
    private ServletContext ctx;
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	public void init(){
		clientsDAO = (ClientsDAO) getServletContext().getAttribute("clientsDAO");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Client newClient = new Level1Clients();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		newClient = clientsDAO.getClientByUsername(username, password);
		session.setAttribute("client", newClient);
		request.getRequestDispatcher("Dashboard").forward(request, response);
		
	}

}
