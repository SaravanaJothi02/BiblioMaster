package repository;

import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository {
    public boolean isAvailBook(String bookTitle) throws SQLException {
        String title=bookTitle.toLowerCase();
        String query="Select * from book where title=?";
        try(Connection connection= DatabaseConnection.getConnection();
            PreparedStatement pst=connection.prepareStatement(query);){
            pst.setString(1,title);
            ResultSet rs=pst.executeQuery();
            return rs.next();
        }
    }

    public void addBook(String bookTitle, int genreId, int authorId, String publicationYear, String bookCount, String imagepath) {
        String query = "INSERT INTO book (title, genreId, authorId, publicationYear, bookCount, bookImg, isExists) " +
                       "VALUES (?,?,?,?,?,?,?)";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);){
            pst.setString(1,bookTitle);
            pst.setInt(2,genreId);
            pst.setInt(3,authorId);
            pst.setInt(4, Integer.parseInt(publicationYear));
            pst.setInt(5,Integer.parseInt(bookCount));
            pst.setString(6,imagepath);
            pst.setInt(7,1);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
