package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import service.LoginService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService service;
    public LoginServlet() {
        super();
        service=new LoginService();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader=request.getReader();
		StringBuilder jsonString=new StringBuilder();
		String line;
		while((line=reader.readLine())!=null) {
			jsonString.append(line);
		}
		JSONObject json=new JSONObject(jsonString.toString());
		String username=json.getString("username");
		String password=json.getString("password");
		boolean isValidLogin=service.validateLogin(username,password);
		JSONObject responseJson=new JSONObject();
		if(isValidLogin) {
			responseJson.put("message","Login successful");
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			responseJson.put("message","Invalid username or password");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		response.getWriter().write(responseJson.toString());
	}

}
