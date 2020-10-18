import Library.Library;
import Library.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    Library library = new Library();
    Book book = new Book("ALICE'S ADVENTURES IN WONDERLAND","Lewis Carroll","Adventure","1865","etext/Alice-in-Wonderland.txt");

    @Test
    void getBooks() {
        library.addBook(book);
        assertEquals(book,library.getBook(0));
    }

    @Test
    void addBook() {
        library.addBook(book);
        assertEquals(book,library.getBook(0));
    }

    @Test
    void removeBook() {
        library.addBook(book);
        library.removeBook(0);
        assertNull(library.getBook(0));
    }

    @Test
    void getBook() {
        library.addBook(book);
        assertEquals(book,library.getBook(0));
    }

    @Test
    void loadLibraryFromCSV() {
        library.loadLibraryFromCSV("booklist.csv");
        assertEquals(3,library.getBooks().size());
    }
}