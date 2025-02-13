package servlet;

import org.json.JSONObject;
import service.BookAvailabilityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/isBookAvailable")
public class IsBookAvailableServlet extends HttpServlet {
    private BookAvailabilityService service;
    public IsBookAvailableServlet(){
        service=new BookAvailabilityService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookTitle=request.getParameter("title");
        response.setContentType("application/json");
        JSONObject json = new JSONObject();
        try {
            boolean isBookAvailable = service.isAvailBook(bookTitle);
            json.put("isAvailable", isBookAvailable);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(json.toString());
        }
        catch (SQLException e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            json.put("message","Internal server error");
            response.getWriter().write(json.toString());
        }
    }

    //if DB connected response is "isAvailable":true/false
    // else "message": Internal server error
}
