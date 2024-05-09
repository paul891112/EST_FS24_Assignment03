public class Book {
    private String title;
    private int rating; // Rating on a scale from 1 to 5

    public Book(String title, int rating) {
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getRating() {
        return rating;
    }
}