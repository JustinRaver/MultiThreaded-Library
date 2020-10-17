import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * BookButton Class is the blueprint for BookButton Objects. This class extends
 * JButton and takes in a Book object and ActionListener in the constructor.
 * Objects are then created and the action listener is added. The BookButton
 * Text is set to the book title and truncated if the title is larger than 20
 * characters. This class has a getButtonBook method that returns the book
 * associated with that button.
 * 
 * CS-121-Version 1.0- Spring 2019
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

		// truncates titles larger than 20 characters with the substring method
		if (myBook.getTitle().length() >= 20) {
			this.setText(myBook.getTitle().toUpperCase().substring(0, 17) + "...");
		} else {
			this.setText(myBook.getTitle().toUpperCase());
		}
		this.addActionListener(myListener);
		this.setToolTipText(myBook.getTitle().toUpperCase());
	}

	/**
	 *
	 * @return Book
	 */
	public Book getButtonBook() {
		return BOOK;
	}
}
