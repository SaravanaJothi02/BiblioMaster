package filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:5500");
        response.setHeader("Access-Control-Allow-Methods","GET,PUT,POST,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers","Content-Type,Authorization");
        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        filterChain.doFilter(request,response);

    }
}
