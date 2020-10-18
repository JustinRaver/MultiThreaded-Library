package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 */
public class BookInfoPanel extends JPanel {
    private final JPanel BOOKDATAPANEL;
    private final ActionListener DATALOADLISTENER;
    private final JTextField FILEENTRYFIELD;

    public BookInfoPanel(ActionListener myListener){
        this.DATALOADLISTENER = myListener;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Book Data"));
        BOOKDATAPANEL = new JPanel();
        BOOKDATAPANEL.setLayout(new BoxLayout(BOOKDATAPANEL, BoxLayout.Y_AXIS));
        JScrollPane bookDataScrollPane = new JScrollPane(BOOKDATAPANEL);
        bookDataScrollPane.setBorder(BorderFactory.createTitledBorder("Book Stats"));
        bookDataScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        bookDataScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel invalidWordPanel = new JPanel();
        invalidWordPanel.setBorder(BorderFactory.createTitledBorder("Invalid Word File"));
        FILEENTRYFIELD = new JTextField(12);
        FILEENTRYFIELD.addActionListener(null);
        invalidWordPanel.add(FILEENTRYFIELD);
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(null);
        loadButton.setToolTipText("Load your invalid word file");
        invalidWordPanel.add(loadButton);
        this.add(bookDataScrollPane, BorderLayout.CENTER);
        this.add(invalidWordPanel,BorderLayout.SOUTH);

    }
}
