package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Genre;
import service.GetGenreService;

/**
 * Servlet implementation class GetGenreServlet
 */
@WebServlet("/getGenres")
public class GetGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GetGenreService service;
    public GetGenreServlet() {
        super();
        service=new GetGenreService();
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		List<Genre> genres=service.getGenres();
		JSONArray genreArray=new JSONArray();
		for(Genre genre:genres) {
			genreArray.put(genre.getGenre());
		}
		JSONObject json=new JSONObject();
		json.put("genres", genreArray);
		response.getWriter().write(json.toString());
	}
	
	
	
	//response 
	
	//{"genres":[jjd,asja,jijaisa]}
}
