package poogleForms.controller.form;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poogleForms.model.form.*;

/**
 * Servlet implementation class CreateForm
 */
@WebServlet("/CreateForm")
public class CreateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getSession().setAttribute("adminUsername", "adminHaha");
		request.getRequestDispatcher("CreateForm.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Question> qList = new ArrayList<Question>();
		
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()){
			String parameterName = names.nextElement();
			String parameterValue = request.getParameter(parameterName);
			System.out.println(parameterName);
			if(parameterName.matches("/[questionNumber]/") || parameterName.contains("questionNumber")){
				if(parameterName.endsWith("MCQ")){
					System.out.println("MCQ match found");
					qList.add(new MultipleChoiceTypeQuestion(45454,parameterValue));
				}
				else if(parameterName.endsWith("Text")){
					System.out.println("Text match found");
					qList.add(new TextTypeQuestion(5646546, parameterValue));
				}
			}
			else if(parameterName.matches("/[option@]/") || parameterName.contains("option@")){
				int quesIndex = Character.getNumericValue(parameterName.charAt(7));
				MultipleChoiceTypeQuestion q = (MultipleChoiceTypeQuestion)qList.get(quesIndex);
				q.addOption(parameterValue);
			}
		}
		
		HttpSession session = request.getSession();
		
		Map<Long,Form> formMap = (Map<Long, Form>) session.getAttribute("createdFormList");
		Form f= new Form(request.getParameter("formName"), qList, (String)session.getAttribute("adminUsername"));
		//formMap.put(f.getID(),f);
		
	}

}
