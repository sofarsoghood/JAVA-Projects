package at.htlinn.ortner.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.htlinn.ortner.db.DBManagement;

/**
 * Servlet implementation class WorkOnSales
 */
@WebServlet("/WorkOnSales")
public class WorkOnSales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkOnSales() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/jsp; charset=UTF-8");
		DBManagement db = new DBManagement();
		String customer = request.getParameter("list");
		String saleAmount = request.getParameter("saleAmount");
		String salePName = request.getParameter("salePPE");
		String producer = request.getParameter("producer");
		
		if(customer!=null && saleAmount!=null && salePName!=null && producer!=null){
			//db.insertSale(saleID, customerID, amount, producerName, price, sellDate)
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
