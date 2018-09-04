package poogleForms.controller.general;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import poogleForms.DAO.ClientsDAO;

/**
 * Servlet implementation class UsernameCheck
 */
@WebServlet("/UsernameCheck")
public class UsernameCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ClientsDAO clientsDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsernameCheck() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("username check servlet called");
		if(clientsDAO.checkIfUsernameExists(request.getParameter("username"))){
			System.out.println("username exists");
			response.getWriter().write("true");
		}
		else{
			System.out.println("username donot exists");
			response.getWriter().write("false");
		}
	}

}
