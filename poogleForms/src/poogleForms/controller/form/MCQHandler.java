package poogleForms.controller.form;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import poogleForms.model.form.Question;

/**
 * Servlet implementation class MCQHandler
 */
@WebServlet("/MCQHandler")
public class MCQHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MCQHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		///response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("mcqhandler opened");
		request.getRequestDispatcher("MCQHandler.jsp").include(request, response);
		//System.out.println("from mcq handler: " + request.getParameter(Long.toString(((Question) (request.getAttribute("currQuestion"))).getID())));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
