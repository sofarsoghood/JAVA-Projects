package at.htlinn.ortner.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.htlinn.ortner.db.DBManagement;

/**
 * Servlet implementation class ProfilData
 */
@WebServlet("/ProfilData")
public class ProfilData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManagement dbManagement = new DBManagement();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nickname = request.getParameter("nickname");
		String oldPW = request.getParameter("oldPassword");
		String newPW1 = request.getParameter("newPassword1");
		String newPW2 = request.getParameter("newPassword2");
		
		String firstName = request.getParameter("name");
		String lastName = request.getParameter("lastname");
		String confirmPassword = request.getParameter("confirmPassword");
		
		int userID = (Integer) request.getSession().getAttribute("userID");
		
		request.getSession().setAttribute("changedNameData", "");
		request.getSession().setAttribute("changedLoginData", "");

		try {
			// entered Data is not allowed to be null
			// the old entered PW must fit the old PW in the database
			// the new entered passwords must be equal to each other
			if((nickname!=null && oldPW!=null && newPW1!=null && newPW2!=null) && (newPW1.equals(newPW2)) && dbManagement.checkOldPassword(oldPW)){
				dbManagement.updateUserLoginData(nickname, newPW1);
				request.getSession().setAttribute("changedLoginData", "Nickname und Login Password geändert !");
				response.sendRedirect("ChangedUserData.jsp");
			}
			if(firstName!=null && lastName!=null && dbManagement.checkOldPassword(confirmPassword)){
				dbManagement.updateUserNameData(firstName, lastName, userID);
				request.getSession().setAttribute("changedNameData", "Private Daten geändert !");
				response.sendRedirect("changedUserData.jsp");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
