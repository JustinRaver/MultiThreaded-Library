package Main;

import GUI.ReaderOfBooksPanel;

import javax.swing.*;
import java.awt.*;

/**
 * This panel is the main Driver Panel for the reader of books project and is
 * where the Frame is created and the Panels.ReaderOfBooksPanel is added. This is the
 * frame that displays the GUI with a Frame title of "Reader of Books".
 * <p>
 * CS-121-Version 1.0- Spring 2019
 *
 * @author Justin Raver
 */

public class ReaderOfBooks {
    public static void main(String[] args) {
        // Creates new JFrame and then sets close operation for user Exit
        JFrame frame = new JFrame("Reader of Books");
        frame.setPreferredSize(new Dimension(1200, 900));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create Panels.ReaderOfBooksPanel and adds the panel to the JFrame
        ReaderOfBooksPanel panel = new ReaderOfBooksPanel();
        frame.getContentPane().add(panel);
        // sizes frame for components
        frame.pack();
        // sets the JFrame to visible
        frame.setVisible(true);
    }

}
