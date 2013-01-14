package at.htlinn.ortner.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;




public class DBManagement {

	private Connection connection;

	//open/close
	private void connect() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphonestore", "root", "root");
	}
	private void close() throws SQLException{
		if(connection!=null){
			connection.close();
		}
	}
	
	//specific methods
	public boolean checkLoginData(String nickname, String password) throws SQLException, ClassNotFoundException{
		boolean match = false;
		this.connect();
		String sql = "SELECT * FROM Users WHERE nickname = ? and password = md5(?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, nickname);
		pstmt.setString(2, password);
		ResultSet matches = pstmt.executeQuery();
		
		if(matches.next()){
			match=true;
		}
		else{
			match = false;
		}
		pstmt.close();
		return match;
		
	}
	public boolean checkOldPassword(String password) throws ClassNotFoundException, SQLException{
		this.connect();
		String sql = "SELECT password FROM users WHERE password=md5(?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, password);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			return true;
		}
		else{
			return false;
		}
	}
	public int getCustomerID(String name) throws ClassNotFoundException, SQLException{
		this.connect();
		String sql = "SELECT customerID FROM Customers WHERE name=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		return rs.getInt(1);
	}
	
	//update methods
	public void updateUserLoginData(String newNickname, String newPassword) throws ClassNotFoundException, SQLException{
		this.connect();
		String sql = "UPDATE Users SET nickname=?, password = md5(?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, newNickname);
		pstmt.setString(2, newPassword);
		pstmt.executeUpdate();
		pstmt.close();
	}
	public void updateUserNameData(String newFirstname, String newLastname, int userID) throws ClassNotFoundException, SQLException{
		this.connect();
		String sql = "UPDATE Users SET firstName=?, lastname =? WHERE userID=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, newFirstname);
		pstmt.setString(2, newLastname);
		pstmt.setInt(3, userID);
		pstmt.executeUpdate();
		pstmt.close();
	}
	public void updateCustomer(int customerID, String name, String city, int zip, String street) throws ClassNotFoundException, SQLException{
		this.connect();
		String sql = "UPDATE Customers SET name=?, city =?  zip=? street=? WHERE userID=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, city);
		pstmt.setInt(3, zip);
		pstmt.setString(4, street);
		pstmt.setInt(5, customerID);
		pstmt.executeUpdate();
		pstmt.close();
	}
	public void updatePurchase(String productName, String seller, String producer, int amount, String purchaseDate, int purchaseID) throws ClassNotFoundException, SQLException{
		this.connect();
		String sql = "UPDATE Purchase SET productName=?, seller =?  producer=? amount=? purchaseDate=? WHERE purchaseID=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, productName);
		pstmt.setString(2, seller);
		pstmt.setString(3, producer);
		pstmt.setInt(4, amount);
		pstmt.setString(5, purchaseDate);
		pstmt.setInt(6, purchaseID);
		pstmt.executeUpdate();
		pstmt.close();
	}
	public void updateSale(int saleID, int customerID, int amount, String productName, double pricePP, String sellDate) throws ClassNotFoundException, SQLException{
		this.connect();
		String sql = "UPDATE Sale SET customerID=?, amount=?  productName=? pricePP=? sellDate=? WHERE saleID=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, customerID);
		pstmt.setInt(2, amount);
		pstmt.setString(3, productName);
		pstmt.setDouble(4, pricePP);
		pstmt.setString(5, sellDate);
		pstmt.setInt(6, saleID);
		pstmt.executeUpdate();
		pstmt.close();
	}

	//select * methods (getter)
	public ArrayList<PartSale> getPartSales() throws SQLException, ClassNotFoundException{
		this.connect();
		String sql = "SELECT * FROM Sale";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<PartSale> partSales = new ArrayList<PartSale>();
		while(rs.next()){
			partSales.add(new PartSale(rs.getInt("saleID"), rs.getInt("customerID"), rs.getInt("amount"), rs.getString("productName"), rs.getDouble("pricePP"), rs.getString("sellDate")));
		}
		return partSales;
	}
	public int getUserID(String nickname, String password) throws ClassNotFoundException, SQLException{
		int userID=0;
		this.connect();
		String sql = "SELECT userID FROM users WHERE nickname= ? and password = md5(?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, nickname);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			userID=rs.getInt("userID");
		}
		return userID;
	}
	public ArrayList<Customer> getCustomers() throws SQLException, ClassNotFoundException{
		this.connect();
		String sql = "SELECT * FROM Customers";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Customer> customers = new ArrayList<Customer>();
		while(rs.next()){
			customers.add(new Customer(rs.getInt("customerID"), rs.getString("name"), rs.getString("city"), rs.getInt("zip"), rs.getString("street")));
		}
		System.out.println(customers.size());
		return customers;
	}
	public ArrayList<PartStock> getPartStocks() throws SQLException, ClassNotFoundException{
		this.connect();
		String sql = "SELECT * FROM Stock";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<PartStock> partstocks = new ArrayList<PartStock>();
		while(rs.next()){
			partstocks.add(new PartStock(rs.getInt("partStockID"), rs.getString("smName"), rs.getInt("amount")));
		}
		stmt.close();
		return partstocks;
	}
	
	//delete methods
	public void deleteUser(int userID) throws SQLException, ClassNotFoundException{
		this.connect();
		String sql = "DELETE FROM Users WHERE userID=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, userID);
		pstmt.executeUpdate();
		pstmt.close();
	}
	public void deleteCustomer(int customerID) throws ClassNotFoundException, SQLException{
		this.connect();
		String sql = "DELETE FROM Customers WHERE customerID=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, customerID);
		pstmt.executeUpdate();
		pstmt.close();
	}
	public void deletePurchase(int purchaseID) throws ClassNotFoundException, SQLException{
		this.connect();
		String sql = "DELETE FROM Purchase WHERE purchaseID=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, purchaseID);
		pstmt.executeUpdate();
		pstmt.close();
	}
	public void deleteSale(int saleID) throws ClassNotFoundException, SQLException{
		this.connect();
		String sql = "DELETE FROM Sale WHERE saleID=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, saleID);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	//insert methods
	public void insertUser(int id, String nickname, String firstName, String lastName, String password) throws SQLException, ClassNotFoundException{
		this.connect();
		String sql = "INSERT INTO Users VALUES(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setString(2, nickname);
		pstmt.setString(3, firstName);
		pstmt.setString(4, lastName);
		pstmt.setString(5, password);
		pstmt.execute(sql);
		pstmt.close();
	}
	public void insertCustomer(int customerID, String name, String city, int zip, String date) throws ClassNotFoundException, SQLException{
		this.connect();
		String sql = "INSERT INTO Users VALUES(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, customerID);
		pstmt.setString(2, name);
		pstmt.setString(3, city);
		pstmt.setInt(4, zip);
		pstmt.setString(5, date);
		pstmt.execute(sql);
		pstmt.close();
	}
	public void insertPurchase(int purchaseID, String productName, String seller, String producer, int amount, String purchaseDate) throws ClassNotFoundException, SQLException{
		this.connect();
		String sql = "INSERT INTO Users VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, purchaseID);
		pstmt.setString(2, productName);
		pstmt.setString(3, seller);
		pstmt.setString(4, producer);
		pstmt.setInt(5, amount);
		pstmt.setString(6, purchaseDate);
		pstmt.execute(sql);
		pstmt.close();
	}
	public void insertSale(int saleID, int customerID, int amount, String producerName, double price, String sellDate) throws ClassNotFoundException, SQLException{
		this.connect();
		String sql = "INSERT INTO Users VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, saleID);
		pstmt.setInt(2, customerID);
		pstmt.setInt(3, amount);
		pstmt.setString(4, producerName);
		pstmt.setDouble(5, price);
		pstmt.setString(6, sellDate);
		pstmt.execute(sql);
		pstmt.close();
	}
}
