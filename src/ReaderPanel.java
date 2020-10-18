import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * ReaderPanel extends JPanel and is the bluePrint for a panel with an
 * information area at the top, a Content area for displaying books in the
 * center, and a NavigationPanel with pageUp and Down buttons to navigate the
 * books in contentArea. It contains ActionListeners for the pageUP/Down Buttons
 * and the ScrollPane ScrollBar in the contentArea as well as several getter
 * setter methods to communicate with other panels. BorderLayout is used for
 * style purposes along with Titled Borders.
 * 
 * CS-121-Version 1.0- Spring 2019
 * 
 * @author Justin Raver
 */

public class ReaderPanel extends JPanel {
	private final JScrollPane CONTENTSCROLLPANE;
	private final JButton PAGEUPBUTTON;
	private final JButton PAGEDOWNBUTTON;
	private final JLabel TITLELABEL;
	private final JLabel BYLABEL;
	private final JLabel PAGELABEL;
	private final JTextArea CONTENTAREA;

	// ReaderPanel constructor
	public ReaderPanel() {
		// sets layout to border layout and adds titled border
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Reader"));
		// creates information panel, adds labels and sets titled border
		// private class instances
		JPanel informationPanel = new JPanel();
		informationPanel.setBorder(BorderFactory.createTitledBorder("Information"));
		TITLELABEL = new JLabel("Title:");
		TITLELABEL.setPreferredSize(new Dimension(300, 20));
		informationPanel.add(TITLELABEL);
		BYLABEL = new JLabel("By:");
		BYLABEL.setPreferredSize(new Dimension(150, 20));
		informationPanel.add(BYLABEL);
		PAGELABEL = new JLabel("Page:");
		PAGELABEL.setPreferredSize(new Dimension(100, 20));
		informationPanel.add(PAGELABEL);
		this.add(informationPanel, BorderLayout.NORTH);
		// creates the contentField and sets margins to center book text
		CONTENTAREA = new JTextArea();
		CONTENTAREA.setMargin(new Insets(10, 80, 10, 10));
		// creates content scrollPane for books display and sets titled border
		CONTENTSCROLLPANE = new JScrollPane(CONTENTAREA);
		CONTENTSCROLLPANE.getVerticalScrollBar().addAdjustmentListener(new ContentAdjustmentListener());
		CONTENTSCROLLPANE.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		CONTENTSCROLLPANE.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		CONTENTSCROLLPANE.setBorder(BorderFactory.createTitledBorder("Content"));
		// adds the content scroll pane to the reader panel
		this.add(CONTENTSCROLLPANE, BorderLayout.CENTER);
		// creates the navigation panel for page up and down and sets titled border
		JPanel navigationPanel = new JPanel();
		navigationPanel.setBorder(BorderFactory.createTitledBorder("Navigation"));
		// creates and adds page up and page down buttons to navigation panel
		PAGEUPBUTTON = new JButton("Page Up");
		PAGEUPBUTTON.addActionListener(new PageButtonListener());
		navigationPanel.add(PAGEUPBUTTON);
		PAGEDOWNBUTTON = new JButton("Page Down");
		PAGEDOWNBUTTON.addActionListener(new PageButtonListener());
		navigationPanel.add(PAGEDOWNBUTTON);
		// adds navigation panel to reader panel
		this.add(navigationPanel, BorderLayout.SOUTH);

	}

	/**
	 * contentAdjustmentListener implements ActionListener and is used to update the
	 * values shown by the pageJLabel it responds to resizing of the page and
	 * adjustment of the scroll bar through mouse interactions and pageUP/pageDown
	 * events. Enables and disables the pageUp/pageDown buttons when top or bottom
	 * of book is reached or book has not yet been loaded.
	 */
	private class ContentAdjustmentListener implements AdjustmentListener {

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			// TODO Auto-generated method stub
			// integer representing the max clicks from top of book to bottom
			int maxInt = CONTENTSCROLLPANE.getVerticalScrollBar().getMaximum();
			// integer representing the amount of clicks to scroll to a new page of text
			int blockIncrement = CONTENTSCROLLPANE.getVerticalScrollBar().getBlockIncrement(1);
			// integer representing the scrollBar value at the current page
			int currentPageValue = CONTENTSCROLLPANE.getVerticalScrollBar().getValue();
			// integer representing the current page number
			int currentPage = ((currentPageValue + blockIncrement) / blockIncrement);
			// integer representing the totalPages of the book
			int totalPages = (maxInt / blockIncrement);
			PAGELABEL.setText("Page: " + currentPage + "/" + totalPages);
			// disables the page up and down buttons when the top and bottom of the book has
			// been reached or if a book has not yet been loaded.
			PAGEUPBUTTON.setEnabled(currentPageValue != 0);
			PAGEDOWNBUTTON.setEnabled(currentPageValue + blockIncrement != maxInt);
		}
	}

	/**
	 * Page button listener implements action listener and is added to the page up
	 * and page down buttons. The listener uses the .getSource method to either
	 * scroll the page up or down by setting the verticalScrollBar value.
	 */
	private class PageButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// gets an integer representing a page of text
			int scrollPaneBlock = CONTENTSCROLLPANE.getVerticalScrollBar().getBlockIncrement(1);
			// gets an integer that represents the current scrollBar value
			int currentScrollValue = CONTENTSCROLLPANE.getVerticalScrollBar().getValue();
			if (e.getSource() == PAGEUPBUTTON) {
				CONTENTSCROLLPANE.getVerticalScrollBar().setValue(currentScrollValue - scrollPaneBlock);
			} else {
				CONTENTSCROLLPANE.getVerticalScrollBar().setValue(currentScrollValue + scrollPaneBlock);
			}
		}

	}

	/**
	 *
	 * @return the PageDownButton
	 */
	public JButton getPageDownButton() {
		return this.PAGEDOWNBUTTON;
	}

	/**
	 * 	@return the contentScrollPane
	 */
	public JScrollPane getContentScrollPane() {
		return CONTENTSCROLLPANE;
	}

	/**
	 * This method takes in book objects as a parameter then uses the book class
	 * method getReader to set the content area text to the book text.This method
	 * catches invalid files and returns a message to the user.
	 * 
	 * @param myBook the book object being passed to the library reader pane
	 */
	public void setContentArea(Book myBook) {
		if (myBook.getReader() == null) {
			JOptionPane.showMessageDialog(null, "Your file could not be found");
		} else {
			try {
				this.CONTENTAREA.read(myBook.getReader(), null);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "An error occured with the reader");
			}
		}
	}

	/**
	 * This method takes in book objects as a parameter then uses the book class
	 * method getTitle to set the text of titleLabel to the book title.
	 * 
	 * @param thisBook Book being passed to the title label
	 */
	public void setTitleLabel(Book thisBook) {
		this.TITLELABEL.setText("Title: " + thisBook.getTitle());
	}

	/**
	 * This method takes in book objects as a parameter then uses the book class
	 * method getAuthor to set the text of byLabel to the books author.
	 * 
	 * @param thisBook Book object used to set the author label
	 */
	public void setByLabel(Book thisBook) {
		this.BYLABEL.setText("By: " + thisBook.getAuthor());
	}
}
