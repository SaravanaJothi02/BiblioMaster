package servlet;

import service.AddBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/addBook")
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
                maxFileSize = 1024*1024*10,
                maxRequestSize = 1024*1024*50)
public class AddBookServlet extends HttpServlet {
    private AddBookService service;
    private String uploadPath;

    public AddBookServlet(){
        service=new AddBookService();
    }

    @Override
    public void init() throws ServletException {
        uploadPath=getServletContext().getRealPath("/resources/uploads");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookTitle=request.getParameter("title");
        String genre=request.getParameter("genre");
        String author=request.getParameter("author");
        String publicationYear=request.getParameter("year");
        String bookCount=request.getParameter("count");
        Part file=request.getPart("bookImg");
        String fileName=extractFileName(file);
        service.addBookDetails(bookTitle,genre,author,publicationYear,bookCount,file,fileName,uploadPath);
    }

    public String extractFileName(Part file){
        String partHeader=file.getHeader("content-disposition");
        System.out.println(partHeader);
        String imageData[]= partHeader.split(";");
        System.out.println(Arrays.toString(imageData));
        for(String s:imageData){
            if(s.trim().startsWith("filename")){
                return s.substring(s.indexOf("=")+2,s.length()-1);
            }
        }
        return "";
    }
}
