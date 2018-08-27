package poogleForms.controller.form;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import poogleForms.DAO.FormDAO;
import poogleForms.model.form.Form;

/**
 * Servlet implementation class DisplayAllForms
 */
@WebServlet("/DisplayAllForms")
public class DisplayAllForms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	FormDAO formDAO ;
	
    public DisplayAllForms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String displayIndexString = request.getParameter("displayIndex");
		int displayIndex=0;
		
		if(displayIndexString==null){
			displayIndexString= "1";
		}
		displayIndex = Integer.parseInt(displayIndexString);
		
		ArrayList<Long> formIDsList =new ArrayList<Long>(formDAO.getAllFormIDs());
		ArrayList<Form> forms = new ArrayList<Form>();
		
		for(int i =displayIndex*10 -1 ;i<formIDsList.size() && i < displayIndex*10 + 9;i++){
			forms.add(formDAO.getForm(formIDsList.get(i)));
		}
		
		request.setAttribute("forms", forms);
		
		
		request.getRequestDispatcher("displayAllForms.jsp?displayIndex=" + displayIndexString).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
