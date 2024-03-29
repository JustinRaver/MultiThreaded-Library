package GUI;

import Library.Book;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * This class represents a BookInfo panel to be used in coordination with the Library
 * panel to load book data when users click on book buttons
 */
public class BookInfoPanel extends JPanel {
    private final JPanel BOOKDATAPANEL;
    private final JLabel LINECOUNTFIELD;
    private final JLabel WORDCOUNTFIELD;

    /**
     * constructor for basic book info panel object
     * which contains a book stats panel and wordlist panel
     */
    public BookInfoPanel() {
        //sets layout for top level panel
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Book Data"));

        //creates panel to contain line count and word count
        JPanel statPanel = new JPanel();
        statPanel.setBorder(BorderFactory.createTitledBorder("Length Info"));
        //creates label to store line count
        LINECOUNTFIELD = new JLabel("Total Lines: ");
        statPanel.add(LINECOUNTFIELD, BorderLayout.NORTH);
        //creates label to store word count
        WORDCOUNTFIELD = new JLabel("Total Words: ");
        statPanel.add(WORDCOUNTFIELD, BorderLayout.SOUTH);

        //creates a panel to contain the top 100 words
        BOOKDATAPANEL = new JPanel();
        BOOKDATAPANEL.setLayout(new BoxLayout(BOOKDATAPANEL, BoxLayout.Y_AXIS));
        //scroll pane for words with vertical scroll
        JScrollPane bookDataScrollPane = new JScrollPane(BOOKDATAPANEL);
        bookDataScrollPane.setBorder(BorderFactory.createTitledBorder("Top 100 words"));
        bookDataScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        bookDataScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bookDataScrollPane.getVerticalScrollBar().setBlockIncrement(100);

        //adds the statPanel and scroll pane to the main container
        this.add(statPanel, BorderLayout.NORTH);
        this.add(bookDataScrollPane, BorderLayout.CENTER);

    }

    /**
     * @param book the book connected to the button pushed by user
     */
    public void setLINECOUNTFIELD(Book book) {
        NumberFormat numForm = NumberFormat.getInstance(Locale.US);
        this.LINECOUNTFIELD.setText("Total Lines: " + numForm.format(book.getLineCount()));
    }

    /**
     * @param book the book connected to the button pushed by user
     */
    public void setWORDCOUNTFIELD(Book book) {
        NumberFormat numForm = NumberFormat.getInstance(Locale.US);
        this.WORDCOUNTFIELD.setText("Total Words: " + numForm.format(book.getWordCount()));
    }

    /**
     * creates buttons to contain all 100 words in the top wordlist and number of occurrences
     *
     * @param book the book connected to the button pushed by user
     */
    public void setWordArea(Book book) {
        BOOKDATAPANEL.removeAll();
        for (String s : book.getTopWordList(100)) {
            WordButton button = new WordButton(null, s + ":   " + book.getBookData().get(s));
            BOOKDATAPANEL.add(button);
            revalidate();
            repaint();
        }
    }

    /**
     * Sets all attributes for BookInfoPanel
     *
     * @param book the book the user wants to view
     */
    public void setAttributes(Book book) {
        this.setLINECOUNTFIELD(book);
        this.setWORDCOUNTFIELD(book);
        this.setWordArea(book);
    }
}
