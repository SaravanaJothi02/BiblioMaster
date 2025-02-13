package servlet;

import model.Author;
import org.json.JSONArray;
import org.json.JSONObject;
import service.GetAuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/getAuthors")
public class GetAuthorServlet extends HttpServlet {
    private GetAuthorService service;
    public GetAuthorServlet(){
        service=new GetAuthorService();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject json=new JSONObject();
        try{
            List<Author> authorList=service.getAuthors();
            JSONArray authorArray=new JSONArray();
            for(Author author:authorList){
                authorArray.put(author.getAuthorName());
            }
            json.put("authors",authorArray);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(json.toString());
        }
        catch (SQLException e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            json.put("message","Database Connection error");
        }
    }
}
