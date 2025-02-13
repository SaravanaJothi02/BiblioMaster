package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.Admin;
import util.DatabaseConnection;

public class AdminRepository {
	public AdminRepository() {
		
	}
	public Admin validateLogin(String username, String password) {
		String query="Select * from admin where username=?";
		try(Connection connection= DatabaseConnection.getConnection();
			PreparedStatement pst=connection.prepareStatement(query);){
			pst.setString(1,username);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				return new Admin(rs.getString("username"),
						rs.getString("password"),rs.getString("mailID"));
			}
			else {
				return null;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
