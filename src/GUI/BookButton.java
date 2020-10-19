package GUI;

import Library.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * GUI.BookButton Class is the blueprint for GUI.BookButton Objects. This class extends
 * JButton and takes in a Library.Library.Book object and ActionListener in the constructor.
 * Objects are then created and the action listener is added. The GUI.BookButton
 * Text is set to the book title and truncated if the title is larger than 20
 * characters. This class has a getButtonBook method that returns the book
 * associated with that button.
 *
 * 
 * @author Justin Raver
 */

public class BookButton extends JButton {

	// private book class instance
	private final Book BOOK;

	/**
	 *
	 * @param myBook the book instance being passed to this button
	 * @param myListener the listener instance being passed to this button
	 */
	public BookButton(Book myBook, ActionListener myListener) {
		this.BOOK = myBook;
		this.setFont(new Font("Arial", Font.PLAIN, 15));
		// truncates titles larger than 20 characters with the substring method
		if (myBook.getTitle().length() >= 20) {
			this.setText(myBook.getTitle().toUpperCase().substring(0, 17) + "...");
		} else {
			this.setText(myBook.getTitle().toUpperCase());
		}
		//sets the attributes
		this.setPreferredSize(new Dimension(230, 40));
		this.setMaximumSize(new Dimension(230, 40));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.addActionListener(myListener);
		this.setToolTipText(myBook.getTitle().toUpperCase());
	}

	/**
	 *
	 * @return Library.Library.Book the book connected to this button
	 */
	public Book getButtonBook() {
		return BOOK;
	}
}
