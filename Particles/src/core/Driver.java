package core;

import javax.swing.*;
import java.awt.*;

public class Driver {
	public static void main(String[] args) {
        // Create the frame.
        JFrame frame = new JFrame();
        frame.setTitle("Particle Test");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.requestFocus();

        // Create the screen.
        Screen screen = new Screen();
        screen.setSize(new Dimension(frame.getWidth(), frame.getHeight()));
        screen.setFocusable(true);
        screen.setVisible(true);
        screen.setBackground(Color.black);
        frame.add(screen);

        frame.setVisible(true);
	}
}
