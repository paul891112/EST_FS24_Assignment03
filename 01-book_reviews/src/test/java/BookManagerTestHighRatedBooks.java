import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookManagerTestHighRatedBooks {

    private BookRatingsFetcher fetcher = mock(BookRatingsFetcher.class);

    // Base Case
    @Test
    void highRatedBooksTestBaseCase(){
        // create a list of books, one with a too low rating
        Book book1 = new Book("Book1HighRating", 5, "Author1");
        Book book2 = new Book("Book2HighRating", 5, "Author2");
        Book book3 = new Book("Book3LowRating", 1, "Author3");
        List<Book> listOfBooks = Arrays.asList(book1, book2, book3);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertThat(bookManager.highRatedBooks()).containsExactlyInAnyOrder(book1, book2);
    }

    // T1
    @Test
    void highRatedBooksTestNullList(){
        List<Book> listOfBooks = null;

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertThrows(NullPointerException.class, () -> {
            bookManager.highRatedBooks();
        });
    }

    //T2
    @Test
    void highRatedBooksTestEmptyList(){
        List<Book> listOfBooks = List.of(new Book[0]);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertEquals(0, bookManager.highRatedBooks().size());
    }

    // T3
    @Test
    void highRatedBooksTestLowRatingLengthOne(){
        Book book1 = new Book("Book1LowRating", 3, "Author1");
        List<Book> listOfBooks = Arrays.asList(book1);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertEquals(0, bookManager.highRatedBooks().size());
    }

    // T4
    @Test
    void highRatedBooksTestHighRatingLengthOne(){
        Book book1 = new Book("Book1HighRating", 4, "Author1");
        List<Book> listOfBooks = Arrays.asList(book1);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertThat(bookManager.highRatedBooks()).containsExactlyInAnyOrder(book1);
    }

    // T5
    @Test
    void highRatedBooksTestOnlyLowLengthTwo(){
        Book book1 = new Book("Book1LowRating", 1, "Author1");
        Book book2 = new Book("Book2LowRating", 2, "Author2");
        List<Book> listOfBooks = Arrays.asList(book1, book2);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertEquals(0, bookManager.highRatedBooks().size());
    }

    // T5
    @Test
    void highRatedBooksTestOnlyHighLengthTwo(){
        Book book1 = new Book("Book1HighRating", 5, "Author1");
        Book book2 = new Book("Book2HighRating", 5, "Author2");
        List<Book> listOfBooks = Arrays.asList(book1, book2);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertThat(bookManager.highRatedBooks()).containsExactlyInAnyOrder(book1, book2);
    }

    // T5
    @Test
    void highRatedBooksTestHighAndLowLengthTwo(){
        Book book1 = new Book("Book1HighRating", 4, "Author1");
        Book book2 = new Book("Book2LowRating", 3, "Author2");
        List<Book> listOfBooks = Arrays.asList(book1, book2);

        when(fetcher.all()).thenReturn(listOfBooks);
        BookManager bookManager = new BookManager(fetcher);

        assertThat(bookManager.highRatedBooks()).containsExactlyInAnyOrder(book1);
    }
}
