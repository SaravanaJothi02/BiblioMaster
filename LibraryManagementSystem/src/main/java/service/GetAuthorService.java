package service;

import model.Author;
import repository.AuthorRepository;

import java.sql.SQLException;
import java.util.List;

public class GetAuthorService {
    private AuthorRepository repository;
    public GetAuthorService(){
        repository=new AuthorRepository();
    }
    public List<Author> getAuthors() throws SQLException {
        return repository.getAuthors();
    }
}
