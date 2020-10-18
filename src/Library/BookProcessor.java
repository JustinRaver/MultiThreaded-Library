package Library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class BookProcessor {
    private HashSet<String> invalidSet;
    private long executionTime;

    public BookProcessor(){
        invalidSet = null;
        executionTime = 0;
    }

    public long getExecutionTime(){
        return executionTime;
    }

    /**
     *
     * @param fileName the name of the book being passed in
     * @return HashSet of invalid words
     */
    public HashSet<String> createInvalidSet(String fileName){
        HashSet<String> set = new HashSet<>();
        try {
            Scanner scan = new Scanner(new File("resources/InvalidLists/"+fileName));
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
            System.out.println(e.toString());
        }
        invalidSet = set;
        return set;
    }

    /**
     *
     * @param book text we want to process
     */
    public void getBookData(Book book){
        try {
            Scanner scan = new Scanner(new File("resources/"+book.getFilename()));
            //Starting execution timer
            executionTime = System.nanoTime();
            int count = 0;
            while(scan.hasNextLine()){
                //non case sensitive word matching
                StringBuilder str = new StringBuilder();
                str.append(scan.nextLine().toLowerCase());
                count++;
                if(count == 100) {
                    cleanAndCount(str.toString(), book);
                    str.setLength(0);
                    count = 0;
                }
                book.incrementLineCount();
            }
            scan.close();
            //execution time in milliseconds
            executionTime = (System.nanoTime()-executionTime)/1000000;
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }


    public void cleanAndCount(String str,Book book){
        str = str.replaceAll("[^a-zA-Z\\s]", "");
        countWords(str.replaceAll(" +", " "),book);
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
