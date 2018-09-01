package poogleForms.controller.form;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import poogleForms.DAO.FormDAO;
import poogleForms.model.form.Form;
import poogleForms.model.form.MultipleChoiceTypeQuestion;
import poogleForms.model.form.Question;
import poogleForms.model.form.TextTypeQuestion;

/**
 * Servlet implementation class CreateQuestion
 */
@WebServlet("/CreateQuestion")
public class CreateQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FormDAO formDAO;  
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(){
    	/*ServletContext ctx = getServletContext();
    	try {
			formDAO = FormDAO.getFormDAO(ctx.getAttribute("DB_URL").toString(), ctx.getAttribute("DB_Username").toString(), ctx.getAttribute("DB_Password").toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB initialzters errors");
			e.printStackTrace();
		}*/
    	String DB_URL = "jdbc:sqlserver://ggku3ser2;instanceName=SQL2016;databaseName=poogleForms";
		String DB_User = "sa";
		String DB_Password = "Welcome@1234";
		try {
			formDAO = FormDAO.getFormDAO(DB_URL, DB_User, DB_Password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String questionPrompt = request.getParameter("questionPrompt");
		String optionsString = request.getParameter("options");
		Long formID = Long.parseLong(request.getParameter("formID"));
		String[] options = optionsString.split(";");
		Question q = null;
		
		if(request.getParameter("questionType").equals("MCQ")){
			q= new MultipleChoiceTypeQuestion();
			q.setPrompt(questionPrompt);
			q.setFormID(formID);
			((MultipleChoiceTypeQuestion)(q)).setOptions(new ArrayList<String>(Arrays.asList(options)));
		}
		else if(request.getParameter("questionType").equals("TextTypeQuestion")){
			q= new TextTypeQuestion();
			q.setPrompt(questionPrompt);
			q.setFormID(formID);
		}
		
		formDAO.addQuestionToDB(q);
		
		request.setAttribute("currQuestion", q);
		
		System.out.println(request.getParameter("questionPrompt"));
		System.out.println(request.getParameter("questionType"));
		System.out.println(request.getParameter("options"));
		System.out.println(request.getParameter("formID"));
		
		request.getRequestDispatcher(q.getHandler()).forward(request, response);
		
	}

}