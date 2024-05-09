import java.util.List;
import static java.util.stream.Collectors.toList;

public class BookManager {
    public List<Book> highRatedBooks() {
        DatabaseConnection dbConnection = new DatabaseConnection();
        BookRatingsFetcher fetcher = new BookRatingsFetcher(dbConnection);

        try {
            List<Book> allBooks = fetcher.all();
            return allBooks.stream()
                    .filter(book -> book.getRating() >= 4)
                    .collect(toList());
        } finally {
            fetcher.close();
        }
    }
}
