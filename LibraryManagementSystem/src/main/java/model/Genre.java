package model;

public class Genre {
	private static int id;
	private int genreId;
	
	private String genre;
	
	public Genre(int genreId, String genre) {
		this.genreId = id;
		this.genre = genre;
	}
	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	
}
