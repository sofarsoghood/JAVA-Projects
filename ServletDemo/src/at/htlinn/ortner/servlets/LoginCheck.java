package at.htlinn.ortner.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.htlinn.ortner.db.DBManagement;
import at.htlinn.ortner.db.PartStock;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/html; charset=UTF-8");
		
		response.setContentType("text/jsp; charset=UTF-8");
		DBManagement db = new DBManagement();
		String nickname = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
	
		session.setAttribute("username", nickname);
		
		try {
			if(db.checkLoginData(nickname, password)){
				ArrayList<PartStock> partstocks =  db.getPartStocks();
				session.setAttribute("partstocks", partstocks);
				session.setAttribute("userID", db.getUserID(nickname, password));
				session.setAttribute("customers", db.getCustomers());
				
				session.setAttribute("partsales", db.getPartSales());
				
				response.sendRedirect("Stock.jsp");
			}
			else{
				response.sendRedirect("FailLogin.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
