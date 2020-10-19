import Library.Book;
import Library.BookProcessor;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class BookProcessorTest {
    BookProcessor processor = new BookProcessor();
    Book book = new Book("In Search of Lost Time","Marcel Proust","Modern","1913","etext/In-Search-of-Lost-Time.txt");

    String s = "the, dog: =+ a[;e and the &090";
    @Test
    void createInvalidSet() {
        HashSet<String> set = new HashSet<>();
        set.add("the");
        set.add("be");
        set.add("to");
        set.add("of");
        assertEquals(set,processor.createInvalidSet("invalid2.csv"));
    }

    @Test
    void getBookData(){
        processor.createInvalidSet("invalid1.csv");
        processor.getBookData(book);
        System.out.println(processor.getExecutionTime());
        assertTrue(book.getWordCount() > 0);
    }

    @Test
    void cleanString() {
        processor.cleanAndCount(s,book);
    }

    @Test
    void countWords() {
        processor.cleanAndCount(s,book);
        assertEquals(5,book.getWordCount());
    }
}