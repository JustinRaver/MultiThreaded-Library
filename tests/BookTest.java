import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    Book book = new Book("ALICE'S ADVENTURES IN WONDERLAND","Lewis Carroll","Adventure","1865","etext/Alice-in-Wonderland.txt");
    @Test
    void getTitle() {
        assertEquals("ALICE'S ADVENTURES IN WONDERLAND",book.getTitle());
    }

    @Test
    void getAuthor() {
        assertEquals("Lewis Carroll",book.getAuthor());
    }

    @Test
    void getGenre() {
        assertEquals("Adventure",book.getGenre());
    }

    @Test
    void getFilename() {
        assertEquals("etext/Alice-in-Wonderland.txt",book.getFilename());
    }

    @Test
    void isValid() {
        assertTrue(book.isValid());
    }

    @Test
    void getWordCount(){
        assertEquals(0,book.getTotalWordCount());
    }

    @Test
    void incrementWordCount(){
        book.incrementWordCount();
        assertEquals(1,book.getTotalWordCount());
    }
}