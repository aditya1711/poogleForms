package poogleForms.controller.form;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import poogleForms.DAO.FormDAO;
import poogleForms.model.form.*;

/**
 * Servlet implementation class Form
 */
@WebServlet("/FormHandler")
public class FormHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    FormDAO formDAO ;
    
   /* static Form f;
    static{
    	System.out.println("forma handler opened");
		Question q1 = new MultipleChoiceTypeQuestion("How do you laugh?", new String[] {"haha", "hehe", "help"}, 15478);
		Question q2 = new TextTypeQuestion("How do you cry?", 15479);
		ArrayList<Question> qs = new ArrayList<Question>();
		qs.add(q1);
		qs.add(q2);
		
		try {
			System.out.println(f.toJSONString());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(q2.toJSONString());
			q2 = TextTypeQuestion.getQuestionFromJSONString(q2.toJSONString());
			System.out.println(q2.toString());
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f = new Form("form how do",qs,"hahaAdmin");
    }*/
    
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Form f = formDAO.getForm(Long.parseLong(request.getParameter("formID")));
		request.setAttribute("form", f);
		request.getRequestDispatcher("FormHandler.jsp").include(request, response);
		
		ArrayList<Question> qs = f.getList();
		for(int i=0;i<qs.size();i++){
			System.out.println("question ID: " + qs.get(i).getID());
			System.out.println("from forms handler------> "+request.getParameter(Long.toString(qs.get(i).getID())));
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
