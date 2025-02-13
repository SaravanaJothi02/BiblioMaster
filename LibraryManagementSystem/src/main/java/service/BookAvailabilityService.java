package service;

import repository.BookRepository;

import java.sql.SQLException;

public class BookAvailabilityService {
    private BookRepository repository;
    public BookAvailabilityService(){
        repository=new BookRepository();
    }
    public boolean isAvailBook(String bookTitle) throws SQLException {
        return repository.isAvailBook(bookTitle);
    }
}
