package poogleForms.controller.form;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poogleForms.DAO.AnswersDAO;
import poogleForms.DAO.FormDAO;
import poogleForms.model.clients.Client;
import poogleForms.model.form.*;

/**
 * Servlet implementation class CreateForm
 */
@WebServlet("/CreateForm")
public class CreateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FormDAO formDAO;        
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateForm() {
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
		/*String DB_URL = "jdbc:sqlserver://ggku3ser2;instanceName=SQL2016;databaseName=poogleForms";
		String DB_User = "sa";
		String DB_Password = "Welcome@1234";
		try {
			formDAO = FormDAO.getFormDAO(DB_URL, DB_User, DB_Password);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		formDAO = (FormDAO) getServletContext().getAttribute("formDAO"); 
   	 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		Form form;
		if(request.getParameter("formID")!=null && 
				formDAO.checkForLevel2UsernameAndFormIDPair(((Client)(session.getAttribute("client"))).getLoginCredentials().getUsername(), Long.parseLong(request.getParameter("formID")))){

			form = formDAO.getForm(Long.parseLong(request.getParameter("formID")));
		}else{
			form = (Form) request.getAttribute("form");
		}
		Long formID = (long) 0;
		if(form==null){
			form = new Form();
			form.setAdminUsername(((Client)(session.getAttribute("client"))).getLoginCredentials().getUsername());
			formID =formDAO.addFormPrototypeToDB(form);
			form.setID(formID);
			System.out.println("Form not found in request in create form, Creating a new one");
		}else{
			formID = form.getID();
		}

		request.setAttribute("form", form);
		request.getRequestDispatcher("CreateForm.jsp").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		/*ArrayList<Question> qList = new ArrayList<Question>();

		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()){
			String parameterName = names.nextElement();
			String parameterValue = request.getParameter(parameterName);
			System.out.println(parameterName);
			if(parameterName.matches("/[questionNumber]/") || parameterName.contains("questionNumber")){
				if(parameterName.endsWith("MCQ")){
					System.out.println("MCQ match found");
					qList.add(new MultipleChoiceTypeQuestion(0, parameterValue));
				}
				else if(parameterName.endsWith("Text")){
					System.out.println("Text match found");
					qList.add(new TextTypeQuestion(parameterValue));
				}
			}
			else if(parameterName.matches("/[option@]/") || parameterName.contains("option@")){
				int quesIndex = Character.getNumericValue(parameterName.charAt(7));
				MultipleChoiceTypeQuestion q = (MultipleChoiceTypeQuestion)qList.get(quesIndex);
				q.addOption(parameterValue);
			}
		}

		HttpSession session = request.getSession();

		Form f= new Form(request.getParameter("formName"), qList, ((Client)(session.getAttribute("client"))).getLoginCredentials().getUsername());

		formDAO.addFormToDB(f);*/
		HttpSession session = request.getSession();
		if(request.getParameter("command").equals("createForm")){
			formDAO.updateFormName(Long.parseLong(request.getParameter("formID")), request.getParameter("formName"));
			//response.sendRedirect("Dashboard");
		}
		else if(request.getParameter("command").equals("deleteForm")){
			if(formDAO.checkForLevel2UsernameAndFormIDPair(((Client)(session.getAttribute("client"))).getLoginCredentials().getUsername(), Long.parseLong(request.getParameter("formID")))){
				formDAO.deleteForm(Long.parseLong(request.getParameter("formID")));
				System.out.println("Sucess delete form");
				response.getWriter().println("Delete Form Success");
				response.getWriter().flush();
				response.flushBuffer();
				
			}
			else{
				System.out.println("unauthorized delete form");
				response.getWriter().println("unauthorized delete Form");
				response.getWriter().flush();
				response.flushBuffer();
			}
		}
		

	}

}
