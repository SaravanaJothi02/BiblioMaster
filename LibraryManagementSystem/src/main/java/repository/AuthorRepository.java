package repository;

import model.Author;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository {
    public List<Author> getAuthors() throws SQLException {

        try(Connection connection= DatabaseConnection.getConnection();
            Statement st=connection.createStatement();){
            List<Author> authors=new ArrayList<>();
            String query="select * from author";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                authors.add(new Author(rs.getInt("authorId"),
                           rs.getString("authorName")));
            }
            return authors;
        }
    }

    public int getAuthorByName(String author) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT authorId FROM author WHERE authorName = ?")) {
            ps.setString(1, author);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("authorId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if not found
    }

    public int add(String author) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO author (authorName) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, author);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // Return the generated ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 in case of failure
    }
}
