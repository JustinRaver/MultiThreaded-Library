package Library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class BookProcessor {
    private final int numBooksProcessed;
    private HashSet<String> invalidSet;
    private long executionTime;

    public BookProcessor(){
        numBooksProcessed = 0;
        invalidSet = null;
        executionTime = 0;
    }

    public long getExecutionTime(){
        return executionTime;
    }
    public HashSet<String> createInvalidSet(String fileName){
        HashSet<String> set = new HashSet<>();
        try {
            Scanner scan = new Scanner(new File("InvalidLists/"+fileName));
            while(scan.hasNextLine()){
                Scanner lineScan = new Scanner(scan.nextLine());
                lineScan.useDelimiter(",");
                while(lineScan.hasNext()){
                    set.add(lineScan.next().toLowerCase());
                }
                lineScan.close();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.toString();
        }
        invalidSet = set;
        return set;
    }

    public void getBookData(Book book){
        try {
            Scanner scan = new Scanner(new File(book.getFilename()));
            //Starting execution timer
            executionTime = System.nanoTime();
            while(scan.hasNextLine()){
                //non case sensitive word matching
                countWords(cleanString(scan.nextLine().toLowerCase()), book);
            }
            scan.close();
            //execution time in milliseconds
            executionTime = (System.nanoTime()-executionTime)/1000000;
        } catch (FileNotFoundException e) {
            e.toString();
        }
    }

    public static String cleanString(String str){
        str = str.replaceAll("[^a-zA-Z\\s]", "");
        return str.replaceAll(" +", " ");
    }

    public void countWords(String str, Book book){
        for(String s:str.split(" ")){
            if(s.equals("")){
                continue;
            }
            if(invalidSet == null || !invalidSet.contains(s)) {
                book.addBookData(s);
            }
        }
    }
}
