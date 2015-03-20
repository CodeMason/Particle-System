package core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Driver {
	public static void main(String[] args) {
        /*
        // Create the frame.
        Frame frame = new JFrame();
        frame.setTitle("Particle Test");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.requestFocus();
        */

        /*
        // Create the frame.
        Frame frame = new JFrame();
        frame.setTitle("Particle Test");
        frame.setSize(512, 512);
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

        screen.setEffect(frame.getSize());

        screen.start();
        */

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Particle Test";
        config.width = 512;
        config.height = 512;
        new LwjglApplication(new Screen(), config);
	}
}
