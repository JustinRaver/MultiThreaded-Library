package GUI;

import Library.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 */
public class BookInfoPanel extends JPanel {
    private final JPanel BOOKDATAPANEL;
    private final ActionListener DATALOADLISTENER;
    private final JLabel LINECOUNTFIELD;
    private final JLabel WORDCOUNTFIELD;
    private final JTextArea WORDAREA;

    public BookInfoPanel(ActionListener myListener){
        //setslayout for top level panel
        this.DATALOADLISTENER = myListener;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Book Data"));
        JPanel statPanel = new JPanel();
        statPanel.setBorder(BorderFactory.createTitledBorder("Length Info"));
        LINECOUNTFIELD = new JLabel("Total Lines: ");
        statPanel.add(LINECOUNTFIELD, BorderLayout.NORTH);

        WORDCOUNTFIELD = new JLabel("Total Words: ");
        statPanel.add(WORDCOUNTFIELD,BorderLayout.SOUTH);
        WORDAREA = new JTextArea();
        BOOKDATAPANEL = new JPanel();
        BOOKDATAPANEL.setLayout(new BoxLayout(BOOKDATAPANEL, BoxLayout.Y_AXIS));
        JScrollPane bookDataScrollPane = new JScrollPane(BOOKDATAPANEL);
        bookDataScrollPane.setBorder(BorderFactory.createTitledBorder("Top 100 words"));
        bookDataScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        bookDataScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bookDataScrollPane.getVerticalScrollBar().setBlockIncrement(100);


        this.add(statPanel,BorderLayout.NORTH);
        this.add(bookDataScrollPane, BorderLayout.CENTER);

    }

    public void setLINECOUNTFIELD(Book book){
        NumberFormat numForm = NumberFormat.getInstance(Locale.US);
        this.LINECOUNTFIELD.setText("Total Lines: "+numForm.format(book.getLineCount()));
    }

    public void setWORDCOUNTFIELD(Book book){
        NumberFormat numForm = NumberFormat.getInstance(Locale.US);
        this.WORDCOUNTFIELD.setText("Total Words: "+numForm.format(book.getWordCount()));
    }

    public void setWORDAREA(Book book){
        BOOKDATAPANEL.removeAll();
        for(String s:book.getTopWordList(100)){
            WordButton button = new WordButton(null,s+ ":   "+ book.getBookData().get(s));
            button.setPreferredSize(new Dimension(230, 40));
            button.setMaximumSize(new Dimension(230, 40));
            button.setAlignmentX(CENTER_ALIGNMENT);
            BOOKDATAPANEL.add(button);
            revalidate();
            repaint();
        }
    }
}
