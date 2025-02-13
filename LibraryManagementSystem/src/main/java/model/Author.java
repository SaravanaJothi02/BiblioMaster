package model;

public class Author {
    private int authorId;
    private String authorName;

    public Author(int authorId,String authorName) {
        this.authorId=authorId;
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
