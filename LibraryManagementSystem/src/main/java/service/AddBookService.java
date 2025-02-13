package service;

import repository.AuthorRepository;
import repository.BookRepository;
import repository.GenreRepository;

import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AddBookService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

    public AddBookService(){
        bookRepository = new BookRepository();
        authorRepository = new AuthorRepository();
        genreRepository = new GenreRepository();
    }

    public void addBookDetails(String bookTitle, String genre, String author, String publicationYear, String bookCount, Part file, String fileName, String uploadPath)  {
        //save image to folder and take relative path of image
        String uniqueFileName= getUniqueFilename(fileName); // image name can be same for different publication books
        saveImage(file, uniqueFileName, uploadPath);

        //get GenreID if genre already present
        int genreId = genreRepository.getGenreByName(genre);
        if(genreId == -1){
            genreId = genreRepository.add(genre);
        }

        // get authorID if author already present
        int authorId = authorRepository.getAuthorByName(author);
        if(authorId == -1){
            authorId = authorRepository.add(author);
        }

        bookRepository.addBook(bookTitle, genreId, authorId, publicationYear, bookCount, "resources/uploads/" + uniqueFileName);
    }

    private String getUniqueFilename(String fileName) {
        int dotIndex=fileName.lastIndexOf('.');
        String fileExtension=fileName.substring(dotIndex);
        return fileName.substring(0,dotIndex)+System.currentTimeMillis()+fileExtension;
    }

    private void saveImage(Part file, String uniqueFileName, String uploadPath)  {
        try(
            InputStream inputStream=file.getInputStream();
            OutputStream outputStream=new FileOutputStream(uploadPath+uniqueFileName)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
