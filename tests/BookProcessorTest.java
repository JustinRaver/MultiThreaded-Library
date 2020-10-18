import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class BookProcessorTest {
    BookProcessor processor = new BookProcessor();
    Book book = new Book("ALICE'S ADVENTURES IN WONDERLAND","Lewis Carroll","Adventure","1865","etext/Alice-in-Wonderland.txt");
    Book book2 = new Book("The Iliad of Homer","Homer","Fiction","750BCE","etext/The-Iliad-of-Homer.txt");

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
        assertTrue(book.getTotalWordCount() > 0);
    }

    @Test
    void cleanString() {
        assertEquals("the dog ae and the ",BookProcessor.cleanString(s));
    }

    @Test
    void countWords() {
        String str = BookProcessor.cleanString(s);
        processor.countWords(str,book);
        assertEquals(5,book.getTotalWordCount());
    }
}