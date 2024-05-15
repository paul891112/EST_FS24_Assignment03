import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookManagerTestUniqueAuthors {

    private BookRatingsFetcher fetcher = mock(BookRatingsFetcher.class);

    // Base Case
    @Test
    void uniqueAuthorsTestBaseCase() {
        Book book1 = new Book("Book1HighRating", 5, "Author1");
        Book book2 = new Book("Book2HighRating", 5, "Author2");
        Book book3 = new Book("Book3LowRating", 1, "Author3");
        List<Book> listOfBooks = Arrays.asList(book1, book2, book3);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertThat(bookManager.uniqueAuthors()).containsExactlyInAnyOrder("Author1", "Author2", "Author3");
    }


    // T1
    @Test
    void uniqueAuthorsTestNullList() {
        List<Book> listOfBooks = null;

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertThrows(NullPointerException.class, () -> {
            bookManager.uniqueAuthors();
        });
    }

    // T2
    @Test
    void uniqueAuthorsTestEmptyList() {
        List<Book> listOfBooks = List.of(new Book[0]);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertEquals(0, bookManager.uniqueAuthors().size());
    }

    // T3
    @Test
    void uniqueAuthorsTestLengthOne() {
        Book book1 = new Book("Book1HighRating", 5, "Author1");
        List<Book> listOfBooks = Arrays.asList(book1);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertThat(bookManager.uniqueAuthors()).containsExactlyInAnyOrder("Author1");
    }

    // T4
    @Test
    void uniqueAuthorsTestLengthTwoUnique() {
        Book book1 = new Book("Book1HighRating", 5, "Author1");
        Book book2 = new Book("Book1HighRating", 5, "Author2");
        List<Book> listOfBooks = Arrays.asList(book1, book2);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertThat(bookManager.uniqueAuthors()).containsExactlyInAnyOrder("Author1", "Author2");
    }

    // T5
    @Test
    void uniqueAuthorsTestLengthTwoDuplicates() {
        Book book1 = new Book("Book1HighRating", 5, "Author1");
        Book book2 = new Book("Book1HighRating", 5, "Author1");
        List<Book> listOfBooks = Arrays.asList(book1, book2);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertThat(bookManager.uniqueAuthors()).containsExactlyInAnyOrder("Author1");
        assertEquals(1, bookManager.uniqueAuthors().size());
    }

    // T6
    @Test
    void uniqueAuthorsTestLength5Unique() {
        Book book1 = new Book("Book1HighRating", 5, "Author1");
        Book book2 = new Book("Book2HighRating", 5, "Author2");
        Book book3 = new Book("Book3LowRating", 1, "Author3");
        Book book4 = new Book("Book3LowRating", 1, "Author4");
        Book book5 = new Book("Book3LowRating", 1, "Author5");
        List<Book> listOfBooks = Arrays.asList(book1, book2, book3, book4, book5);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertThat(bookManager.uniqueAuthors()).containsExactlyInAnyOrder("Author1", "Author2", "Author3", "Author4", "Author5");
    }

    // T7
    @Test
    void uniqueAuthorsTestLength5Duplicates() {
        Book book1 = new Book("Book1HighRating", 5, "Author1");
        Book book2 = new Book("Book2HighRating", 5, "Author2");
        Book book3 = new Book("Book3LowRating", 1, "Author3");
        Book book4 = new Book("Book3LowRating", 1, "Author3");
        Book book5 = new Book("Book3LowRating", 1, "Author1");
        List<Book> listOfBooks = Arrays.asList(book1, book2, book3, book4, book5);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertThat(bookManager.uniqueAuthors()).containsExactlyInAnyOrder("Author1", "Author2", "Author3");
        assertEquals(3, bookManager.uniqueAuthors().size());
    }

}
