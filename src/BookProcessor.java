import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BookProcessor {
    private int numBooksProcessed;
    private List<Book> booksProcessed;
    private HashSet<String> invalidSet;

    public BookProcessor(Book book,String invalidFile){
        numBooksProcessed = 0;
        booksProcessed = new ArrayList<>();
        invalidSet = null;
    }
    public HashSet<String> createInvalidSet(String fileName){
        HashSet<String> set = new HashSet<>();
        try {
            Scanner scan = new Scanner(new File("InvalidLists/"+fileName));
            while(scan.hasNextLine()){
                Scanner lineScan = new Scanner(scan.nextLine());
                lineScan.useDelimiter(",");
                while(lineScan.hasNext()){
                    String word = lineScan.next().toLowerCase();
                    set.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            e.toString();
        }
        invalidSet = set;
        return set;
    }



}
