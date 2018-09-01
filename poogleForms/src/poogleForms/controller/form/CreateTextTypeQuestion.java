package poogleForms.controller.form;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import poogleForms.model.form.Form;
import poogleForms.model.form.Question;
import poogleForms.model.form.TextTypeQuestion;

/**
 * Servlet implementation class TextTypeQuestion
 */
@WebServlet("/CreateTextTypeQuestion")
public class CreateTextTypeQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTextTypeQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("textTypehandler opened");
		
		request.setAttribute("currQuestionType", "textTypeQuestion");
		request.setAttribute("callingPage", "CreateTextTypeQuestion");
		
		request.getRequestDispatcher("CreateMCQ.jsp").include(request, response);
		//System.out.println("from textType handler: " + request.getParameter(Long.toString(((Question) (request.getAttribute("currQuestion"))).getID())));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TextTypeQuestion tq = new TextTypeQuestion();
		
		//tq.setFormID(((Form)(request.getAttribute("form"))).getID());
		
		tq.setPrompt(request.getParameter("questionName"));
		
		request.setAttribute("currQuestion", tq);
		
		System.out.println(request.getAttribute("currQuestion"));
		
	}

}
