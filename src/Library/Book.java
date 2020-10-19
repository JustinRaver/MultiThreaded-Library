package Library;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * This class is the blueprint for an object called Library.Library.Book with the attributes
 * title, author, genre, filename. The class includes getters and setters for
 * all of the methods. A toString that prints books attributes. A isValid method
 * that checks that the attributes aren't null and the file exists. The class
 * implements BookInterface.
 *
 * 
 * @author Justin Raver
 */
public class Book {

	// instance variable

	private final String TITLE;
	private final String AUTHOR;
	private final String GENRE;
	private final String RELEASEDATE;
	private final String FILENAME;
	private int wordCount;
	private int lineCount;
	private BufferedReader fileIn;
	private final HashMap<String,Integer> bookData;

	/**
	 *
	 * @param title the title of the book being created
	 * @param author the author of the book being created
	 * @param genre the genre of the book being created
	 * @param fileName the fileName of the book being created
	 */
	public Book(String title, String author,String genre,String releaseDate,String fileName) {
		this.TITLE = title;
		this.AUTHOR = author;
		this.GENRE = genre;
		this.RELEASEDATE = releaseDate;
		this.FILENAME = fileName;
		this.fileIn = null;
		this.wordCount = 0;
		this.lineCount = 0;
		this.bookData = new HashMap<>();
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

	public String getReleaseDate(){
		return RELEASEDATE;
	}

	public HashMap<String,Integer> getBookData(){
		return this.bookData;
	}

	public int getWordCount(){ return wordCount;}

	public int getLineCount(){
		return this.lineCount;
	}

	public Set<String> getWords(){
		return bookData.keySet();
	}

	//setters
	public void incrementWordCount(){
		wordCount++;
	}

	public void incrementLineCount(){
		lineCount++;
	}

	/**
	 *
	 * @param k the k most common words in the HashMap
	 * @return List of the top k words
	 */
	public List<String> getTopWordList(int k){
		int max = Integer.MIN_VALUE;
		//go thru the hashmap and find the larges value in
		//set of values
		for(Integer i:bookData.values()){
			if(i>max){
				max = i;
			}
		}
		//creates buckets from 0 to the greatest value in hashmap+1
		List<String>[] bucket = new List[max+1];
		//go thru the map and sort words into buckets based of the number of occurrences
		for(Map.Entry<String,Integer> set: bookData.entrySet()){
			int frequency = set.getValue();
			if(bucket[frequency] == null){
				bucket[frequency]= new ArrayList<>();
			}
			bucket[frequency].add(set.getKey());
		}
		//go from the end of the array and populate list from buckets until list is full
		List<String> retSet = new ArrayList<>();
		for(int i= bucket.length-1;i>=0;i--){
			if(bucket[i] != null){
				Collections.sort(bucket[i]);
				for(int j=0;j<bucket[i].size() && retSet.size()<k;j++){
					retSet.add(bucket[i].get(j));
				}
			}
		}
		return retSet;
	}

	/**
	 * @param s the string to be inserted into the hashmap
	 */
	public synchronized void addBookData(String s){
		bookData.put(s,bookData.getOrDefault(s,0)+1);
		incrementWordCount();
	}

	/**
	 * @return String
	 */
	public String toString() {
		return (" Title: " + TITLE + " Author: " + AUTHOR + " Genre: " + GENRE + " FileName: " + FILENAME);
	}

	/**
	 * checks for elements are set and file exists
	 * @return boolean
	 */
	public boolean isValid() {

		if (TITLE != null && AUTHOR != null && GENRE != null && FILENAME != null) {
			File file = new File("resources/"+FILENAME);
			return file.exists() && file.isFile();
		}
		return false;
	}

	/**
	 * @return bufferedReader
	 */
	public BufferedReader getReader() {
		if (!isValid()) {
			return null;
		} else {
			try {
				fileIn = new BufferedReader(new FileReader("resources/"+FILENAME));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "An error occurred with the reader");
			}
			return fileIn;
		}
	}
}
