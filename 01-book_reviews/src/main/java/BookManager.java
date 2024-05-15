import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class BookManager {

    public BookRatingsFetcher fetcher;

    public BookManager(BookRatingsFetcher fetcher) {
        this.fetcher = fetcher;
    }

    public List<Book> highRatedBooks() {

        try {
            List<Book> allBooks = fetcher.all();

            if (allBooks == null){
                throw new NullPointerException("The list of books, fetched from the Database is null.");
            }

            return allBooks.stream()
                    .filter(book -> book.getRating() >= 4)
                    .collect(toList());
        } finally {
            fetcher.close();
        }
    }

    public List<String> uniqueAuthors() {

        try {
            List<Book> allBooks = fetcher.all();

            if (allBooks == null){
                throw new NullPointerException("The list of books, fetched from the Database is null.");
            }

            List<String> authorList = new ArrayList<>();
            for (int i = 0; i < allBooks.size(); i ++){
                authorList.add(allBooks.get(i).getAuthor());
            }


            return authorList.stream()
                    .distinct()
                    .collect(toList());

        } finally {
            fetcher.close();
        }
    }
}
