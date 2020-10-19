package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WordButton extends JButton {

    public WordButton(ActionListener listener,String s){
        this.setFont(new Font("Arial", Font.PLAIN, 15));
        if (s.length() >= 20) {
            this.setText(s.toUpperCase().substring(0, 17) + "...");
        } else {
            this.setText(s.toUpperCase());
        }
        //adds action listener and sets button attributes
        this.addActionListener(listener);
        this.setPreferredSize(new Dimension(230, 40));
        this.setMaximumSize(new Dimension(230, 40));
        this.setAlignmentX(CENTER_ALIGNMENT);
    }
}
