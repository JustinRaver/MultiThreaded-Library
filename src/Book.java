import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * This class is the blueprint for an object called Book with the attributes
 * title, author, genre, filename. The class includes getters and setters for
 * all of the methods. A toString that prints books attributes. A isValid method
 * that checks that the attributes aren't null and the file exists. The class
 * implements BookInterface.
 * 
 * CS-121-Version 2.0 Spring 2019
 * 
 * @author Justin Raver
 */
public class Book {

	// instance variable

	private final String TITLE;
	private final String AUTHOR;
	private final String GENRE;
	private final String FILENAME;
	private BufferedReader fileIn;

	/**
	 *
	 * @param title the title of the book being created
	 * @param author the author of the book being created
	 * @param genre the genre of the book being created
	 * @param fileName the fileName of the book being created
	 */
	public Book(String title, String author,String genre,String fileName) {
		this.TITLE = title;
		this.AUTHOR = author;
		this.GENRE = genre;
		this.FILENAME = fileName;
		this.fileIn = null;
	}

	// getters

	public String getTitle() {
		return TITLE;
	}

	public String getAuthor() {
		return AUTHOR;
	}

	public String getGenre() {
		return GENRE;
	}

	public String getFilename() {
		return FILENAME;
	}

	/**
	 *
	 * @return String
	 */
	public String toString() {
		String attributes;

		attributes = (" Title: " + TITLE + " Author: " + AUTHOR + " Genre: " + GENRE + " FileName: " + FILENAME);

		return attributes;
	}

	/**
	 * checks for elements are set and file exists
	 * @return boolean
	 */
	public boolean isValid() {
		boolean myBool = false;

		if (TITLE != null && AUTHOR != null && GENRE != null && FILENAME != null) {
			File file = new File(FILENAME);

			if (file.exists() && file.isFile()) {
				myBool = true;
			}
			return myBool;
		} else {
			return false;
		}
	}

	/**
	 *
	 * @return bufferedReader
	 */
	public BufferedReader getReader() {
		if (!isValid()) {
			return null;
		} else {
			try {
				fileIn = new BufferedReader(new FileReader(FILENAME));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "An error occurred with the reader");
			}
			return fileIn;
		}
	}
}
