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
    private final JTextField FILEENTRYFIELD;
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
//        LINECOUNTFIELD.setPreferredSize(new Dimension(300, 20));
        statPanel.add(LINECOUNTFIELD, BorderLayout.NORTH);

        WORDCOUNTFIELD = new JLabel("Total Words: ");
//        WORDCOUNTFIELD.setPreferredSize(new Dimension(300, 20));
        statPanel.add(WORDCOUNTFIELD,BorderLayout.SOUTH);
        WORDAREA = new JTextArea();
        BOOKDATAPANEL = new JPanel();
        BOOKDATAPANEL.setLayout(new BoxLayout(BOOKDATAPANEL, BoxLayout.Y_AXIS));
        JScrollPane bookDataScrollPane = new JScrollPane(BOOKDATAPANEL);
        bookDataScrollPane.setBorder(BorderFactory.createTitledBorder("Book Stats"));
        bookDataScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        bookDataScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //bookDataScrollPane.add(WORDAREA,BorderLayout.SOUTH);


        //This is the invalid file input functionality
        JPanel invalidWordPanel = new JPanel();
        invalidWordPanel.setBorder(BorderFactory.createTitledBorder("Invalid Word File"));
        FILEENTRYFIELD = new JTextField(12);
        FILEENTRYFIELD.addActionListener(null);
        invalidWordPanel.add(FILEENTRYFIELD);
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(null);
        loadButton.setToolTipText("Load your invalid word file");
        invalidWordPanel.add(loadButton);
        this.add(statPanel,BorderLayout.NORTH);
        this.add(bookDataScrollPane, BorderLayout.CENTER);
        this.add(invalidWordPanel,BorderLayout.SOUTH);

    }

    public void setLINECOUNTFIELD(Book book){
        NumberFormat numForm = NumberFormat.getInstance(Locale.US);
        this.LINECOUNTFIELD.setText("Total Lines: "+numForm.format(book.getLineCount()));
    }

    public void setWORDCOUNTFIELD(Book book){
        NumberFormat numForm = NumberFormat.getInstance(Locale.US);
        this.WORDCOUNTFIELD.setText("Total Words: "+numForm.format(book.getWordCount()));
    }
}
