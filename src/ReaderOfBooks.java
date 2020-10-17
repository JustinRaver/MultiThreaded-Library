import javax.swing.JFrame;

/**
 * This panel is the main Driver Panel for the reader of books project and is
 * where the Frame is created and the ReaderOfBooksPanel is added. This is the
 * frame that displays the GUI with a Frame title of "Reader of Books".
 * 
 * CS-121-Version 1.0- Spring 2019
 * 
 * @author Justin Raver
 */

public class ReaderOfBooks {
	public static void main(String[] args) {
		// Creates new JFrame and then sets close operation for user Exit
		JFrame frame = new JFrame("Reader of Books");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Creates ReaderOfBooksPanel and adds the panel to the JFrame
		ReaderOfBooksPanel panel = new ReaderOfBooksPanel();
		frame.getContentPane().add(panel);
		// sizes frame for components
		frame.pack();
		// sets the JFrame to visible
		frame.setVisible(true);
	}

}
