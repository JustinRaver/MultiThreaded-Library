package Library;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the blueprint for Library.Library objects. This object has an ArrayList
 * attribute that takes in objects of type book. The methods getBooks, getBook,
 * addBooks,removeBook, and toString methods allow you to add books, get the
 * book locations in index form, remove book at a specific index, and print the
 * books in the ArrayList The class implements LibraryInterface.
 *
 * @author Justin Raver
 */

public class Library {

    //private instance of arraylist to store the books in this
    private final ArrayList<Book> BOOKS;

    /**
     * creates new library with arraylist to store books
     */
    public Library() {
        BOOKS = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return (ArrayList<Book>) BOOKS.clone();
    }

    public void addBook(Book newBook) {
        BOOKS.add(newBook);
    }

    /**
     * @param index removes book at this index
     */
    public void removeBook(int index) {
        if (index >= 0 && index <= BOOKS.size() - 1) {
            BOOKS.remove(index);
        }
    }

    /**
     * @param index index of the book at that index or null if the library is empty
     * @return the book object at index of the ArrayList
     */
    public Book getBook(int index) {
        return (index >= 0 && index <= BOOKS.size() - 1) ? BOOKS.get(index) : null;
    }

    public String toString() {
        StringBuilder allMyBooks = new StringBuilder();
        for (Book myBooks : BOOKS) {
            allMyBooks.append("Index ").append(BOOKS.indexOf(myBooks)).append(myBooks.toString()).append(myBooks.getFilename()).append("\n");
        }
        return allMyBooks.toString();
    }

    /**
     * This method takes in a list of csv book names and populates the library
     *
     * @param csvFilename The file name passed in to read the list of books
     */
    public void loadLibraryFromCSV(String csvFilename) {
        // clears the ArrayList so that new book objects can be added
        BOOKS.clear();

        try {
            // expects booklist.csv or booklist-full.csv
            Scanner fileScan = new Scanner(new File("resources/etext/" + csvFilename));

            while (fileScan.hasNextLine()) {

                Scanner lineScan = new Scanner(fileScan.nextLine());
                lineScan.useDelimiter(",");
                //creating book object using csv data
                Book csvBook = new Book(lineScan.next(), lineScan.next(), lineScan.next(), lineScan.next(), lineScan.next());

                BOOKS.add(csvBook);

                lineScan.close();
            }
            fileScan.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Your file could not be found");
        }
    }

}
