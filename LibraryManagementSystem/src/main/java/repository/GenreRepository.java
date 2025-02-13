package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Genre;
import util.DatabaseConnection;

public class GenreRepository {

	public List<Genre> getGenre(){
		String query="select * from genre";
		List<Genre> genres=new ArrayList<>();
		try(Connection connection= DatabaseConnection.getConnection();
			Statement st=connection.createStatement();){
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
				genres.add(new Genre(rs.getInt("genreId"),rs.getString("genre")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genres;	
	}


	public int getGenreByName(String genre) {
		String query = "select * from genre where genre=?";
		try(Connection con = DatabaseConnection.getConnection();
			PreparedStatement st = con.prepareStatement(query);) {
			st.setString(1, genre);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return rs.getInt("genreId");
			} else {
				return -1;
			}
        } catch (SQLException e) {
			System.out.println("Database connection error");
        }
		return -1;
    }

	public int add(String genre) {
		String query="insert into genre (genre) values(?)";
		try(Connection con=DatabaseConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(query);){
			pst.setString(1,genre.toLowerCase());
			int cnt= pst.executeUpdate();
			if(cnt>0){
				ResultSet rs=pst.getGeneratedKeys();
				if(rs.next()) {
					return rs.getInt("genreId");
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Database connection error");
		}
		return -1;
	}
}
