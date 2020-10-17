import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * This class is the blueprint for Library objects. This object has an ArrayList
 * attribute that takes in objects of type book. The methods getBooks, getBook,
 * addBooks,removeBook, and toString methods allow you to add books, get the
 * book locations in index form, remove book at a specific index, and print the
 * books in the ArrayList The class implements LibraryInterface.
 * 
 * CS-121-Version 2.0- Spring 2019
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

	public void removeBook(int index) {
		if (index >= 0 && index <= BOOKS.size() - 1) {
			BOOKS.remove(index);
		}
	}

	public Book getBook(int index) {
		if (index >= 0 && index <= BOOKS.size() - 1) {
			return BOOKS.get(index);
		} else {
			return null;
		}
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
	 * @param csvFilename The file name passed in to read the list of books
	 */
	public void loadLibraryFromCSV(String csvFilename) {
		// clears the ArrayList so that new book objects can be added
		BOOKS.clear();

		try {
			Scanner fileScan = new Scanner(new File("etext/"+csvFilename));

			while (fileScan.hasNextLine()) {

				String line = fileScan.nextLine();

				Scanner lineScan = new Scanner(line);
				lineScan.useDelimiter(",");

				String title = lineScan.next();
				String author = lineScan.next();
				String genre = lineScan.next();
				String filePath = lineScan.next();

				Book csvBook = new Book(title, author,genre,filePath);
				BOOKS.add(csvBook);

				lineScan.close();
			}
			fileScan.close();

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Your file could not be found");
		}
	}

}
