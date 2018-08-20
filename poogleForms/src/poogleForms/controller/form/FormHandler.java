package poogleForms.controller.form;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

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
    static Form f;
    static{
    	System.out.println("forma handler opened");
		Question q1 = new MultipleChoiceTypeQuestion("How do you laugh?", new String[] {"haha", "hehe", "help"}, 15478);
		Question q2 = new TextTypeQuestion("How do you cry?", 15479);
		ArrayList<Question> qs = new ArrayList<Question>();
		qs.add(q1);
		qs.add(q2);
		
		/*try {
			System.out.println(f.toJSONString());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
