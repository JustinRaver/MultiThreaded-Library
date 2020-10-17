import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class BookProcessorTest {
    BookProcessor processor = new BookProcessor();
    Book book = new Book("ALICE'S ADVENTURES IN WONDERLAND","Lewis Carroll","Adventure","1865","etext/Alice-in-Wonderland.txt");
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
        processor.getBookData(book);
        System.out.println( processor.getExecutionTime());
        System.out.println(book.getWords());
    }

    @Test
    void cleanString() {
        assertEquals("the dog  ae and the ",BookProcessor.cleanString(s));
    }

    @Test
    void countWords() {
        String str = BookProcessor.cleanString(s);
        processor.countWords(str,book);
        assertEquals(5,book.getTotalWordCount());
    }
}