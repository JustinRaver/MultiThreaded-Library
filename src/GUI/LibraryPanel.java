package GUI;

import Library.Book;
import Library.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panels.LibraryPanel extends JPanel and is the blue print for a panel With a
 * JScrollPane for bookButtons and an ImportBooks panel containing a JTextField
 * for File entry and a Load Button to load the desired file. Files are loaded
 * using the csvLoad method from Library.Library and sends and error message to user if
 * invalid input is entered. BorderLayout is used for style purposes along with
 * Titled Borders. The BookButtons are stacked using a GridLayout.
 * 
 * CS-121-Version 1.0- Spring 2019
 * 
 * @author Justin Raver
 */

public class LibraryPanel extends JPanel {

	// class instances
	private final Library MYLIBRARY;
	private final JTextField FILEENTRYFIELD;
	private final JPanel BOOKBUTTONPANEL;
	private final ActionListener BOOKBUTTONLISTENER;

	/**
	 * Panels.LibraryPanel constructor that takes in Action listener as a parameter to
	 * connect the BookButtons to the Panels.ReaderPanel
	 */
	public LibraryPanel(ActionListener myListener) {
		// sets ActionListener instance to the BOOKBUTTONLISTENER in Panels.ReaderOfBooksPanel.
		this.BOOKBUTTONLISTENER = myListener;
		// sets the layout of Panels.LibraryPanel and adds a titled border
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Library"));
		// creates new library object to add book objects to
		MYLIBRARY = new Library();
		// creates a JPanel for the BookButtons and sets layout to GridLayout
		BOOKBUTTONPANEL = new JPanel();
		BOOKBUTTONPANEL.setLayout(new BoxLayout(BOOKBUTTONPANEL, BoxLayout.Y_AXIS));

		/*
		 * creates a jScroll pane containing bookButtonPanel and adds a titled border
		 * sets the verticalScrollPolicy to as needed and turns off the horizontal
		 * scroll.
		 */
		JScrollPane bookListScrollPane = new JScrollPane(BOOKBUTTONPANEL);
		bookListScrollPane.setBorder(BorderFactory.createTitledBorder("Book List"));
		bookListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		bookListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// creates panel for book imports
		JPanel importBookPanel = new JPanel();
		importBookPanel.setBorder(BorderFactory.createTitledBorder("Import Books"));
		// creates text field for CSV entry and adds to importBookPanel
		FILEENTRYFIELD = new JTextField(12);
		FILEENTRYFIELD.addActionListener(new LoadButtonListener());
		importBookPanel.add(FILEENTRYFIELD);
		// creates a load button and adds it to importBookPanel
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new LoadButtonListener());
		loadButton.setToolTipText("Load your desired CSV file");
		importBookPanel.add(loadButton);
		// adds the bookListScrollPane to Panels.LibraryPanel
		this.add(bookListScrollPane, BorderLayout.CENTER);
		// adds the importBookPanel to Panels.LibraryPanel
		this.add(importBookPanel, BorderLayout.SOUTH);

	}

	/*
	 * This listener connected to loadButton in the importBookPanel and
	 * fileEntryField gets the text from the entry field and loads file to myLibrary
	 * using the library CSV load method. Once myLibrary is populated a for loop
	 * iterates through the book objects and creates GUI.BookButton objects for each
	 * book. BookButtonListener is added to the GUI.BookButton objects and the
	 * GUI.BookButton objects are added to the bookButtonPanel
	 */
	private class LoadButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			BOOKBUTTONPANEL.removeAll();
			MYLIBRARY.loadLibraryFromCSV(FILEENTRYFIELD.getText());
			for (Book thisBook : MYLIBRARY.getBooks()) {
				BookButton bookButton = new BookButton(thisBook, BOOKBUTTONLISTENER);
				BOOKBUTTONPANEL.add(bookButton);
				revalidate();
				repaint();
			}
		}
	}
}
