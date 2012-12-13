package aut.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBManagement {

	private Connection connection;

	private void connect() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphonestore", "root", "root");
	}
	private void close() throws SQLException{
		if(connection!=null){
			connection.close();
		}
	}
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
}
