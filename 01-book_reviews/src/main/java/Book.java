public class Book {
    private String title;
    private int rating; // Rating on a scale from 1 to 5
    private String author;

    public Book(String title, int rating, String author) {
        this.title = title;
        this.rating = rating;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public int getRating() {
        return rating;
    }

    public String getAuthor() {
        return author;
    }
}