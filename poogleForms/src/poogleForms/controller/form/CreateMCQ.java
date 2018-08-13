package poogleForms.controller.form;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateMCQ
 */
@WebServlet("/CreateMCQ")
public class CreateMCQ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMCQ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CreateMCQ opened");
		
		request.getRequestDispatcher("CreateMCQ.jsp").include(request, response);
		
		System.out.println(request.getParameter("questionName"));
		ArrayList<String> options = new ArrayList<String>();
		for(int i=0;i<Integer.MAX_VALUE;i++){
			if(request.getParameter("option@" + i)!=null ){
				options.add(request.getParameter("option@" + i));
				System.out.println("option@ " + i + " = " + request.getParameter("option@" + i));
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
