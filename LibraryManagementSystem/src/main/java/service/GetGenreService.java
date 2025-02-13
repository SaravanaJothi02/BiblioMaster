package service;

import java.util.List;

import model.Genre;
import repository.GenreRepository;

public class GetGenreService {
	private GenreRepository repository;
	public GetGenreService() {
		repository=new GenreRepository();
	}
	public List<Genre> getGenres(){
		return repository.getGenre();
	}
}
