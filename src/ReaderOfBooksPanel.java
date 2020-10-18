import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * ReaderOfBooks extends JPanel and is the top-level panel for the reader of
 * books project. This class contains LibraryPanel and ReaderPanel Objects and
 * utilizes a ActionListener (BookButtonListener) that is added to BookButtons
 * in the LibraryPanel and sets the content, title, and author when the
 * BookButtons are clicked.
 * 
 * CS-121-Version 1.0- Spring 2019
 * 
 * @author Justin Raver
 */

public class ReaderOfBooksPanel extends JPanel {

	// instance of ReaderPanel Class
	private final ReaderPanel READERPANEL;

	/**
	 * reader of books contstructor
	 */
	public ReaderOfBooksPanel() {
		/*
		 * sets layout to border layout for ReaderOfBooksPanel and sets a preferred
		 * opening size to accommodate the user
		 */
		this.setPreferredSize(new Dimension(900, 650));
		this.setLayout(new BorderLayout());
		// creates libraryPanel and adds this subPanel to ReaderOfBookPanel.
		// class instances
		LibraryPanel libraryPanel = new LibraryPanel(new BookButtonListener());
		this.add(libraryPanel, BorderLayout.WEST);
		// creates readerPanel and add this subPanel to ReaderOfBooksPanel.
		READERPANEL = new ReaderPanel();
		this.add(READERPANEL, BorderLayout.CENTER);
		BookInfoPanel BOOKINFOPANEL = new BookInfoPanel(new BookButtonListener());
		this.add(BOOKINFOPANEL,BorderLayout.EAST);
	}

	/**
	 * BookButton Listener is connected to the BookButton Objects in LibraryPanel
	 * and the Title, Author, and contentArea in ReaderPanel. This Action listener
	 * gets the book from the button clicked using the BookButton class method
	 * getButtonBook() and then implements the ReaderPanels setTitle(),
	 * setByLabel(), and setContentArea() methods to send book information to these
	 * fields.This method also enables the PageDownButton when the book is loaded
	 * and sets the scrollBar to the top of the page.
	 */
	private class BookButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// sets the text of the titleLabel to the current books title
			READERPANEL.setTitleLabel(((BookButton) e.getSource()).getButtonBook());
			// sets the text of the byLabel to the current books author
			READERPANEL.setByLabel(((BookButton) e.getSource()).getButtonBook());
			// sets the text of the readerPanel textArea to current book text
			READERPANEL.setContentArea(((BookButton) e.getSource()).getButtonBook());
			// sets the scrollPane to the top of the current book
			READERPANEL.getContentScrollPane().getVerticalScrollBar().setValue(0);
		}
	}
}
