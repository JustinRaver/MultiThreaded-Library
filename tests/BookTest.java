import Library.Book;
import Library.BookProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {
    BookProcessor processor = new BookProcessor();
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
    void getWordCount(){
        assertEquals(0,book.getWordCount());
    }

    @Test
    void getLineCount(){
        assertEquals(0,book.getLineCount());
    }

    @Test
    void getTopWordList(){
        processor.createInvalidSet("invalid1.csv");
        processor.getBookData(book);
        for(String s:book.getTopWordList(100)){
            System.out.println(s+":"+book.getBookData().get(s));
        }
        assertEquals(book.getTopWordList(100).size(),100);
    }
    @Test
    void isValid() {
        assertTrue(book.isValid());
    }

    @Test
    void incrementWordCount(){
        book.incrementWordCount();
        assertEquals(1,book.getWordCount());
    }
}