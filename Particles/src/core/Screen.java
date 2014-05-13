package core;

import effect.Effect;
import effect.RainbowSnow;
import utility.InputKeyboard;
import utility.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * Represents a screen on which to draw.
 * @author Valkryst
 * --- Last Edit 9-May-2014
 */
public class Screen extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

    private Thread gameThread;

	private BufferStrategy BS = getBufferStrategy();
	private InputKeyboard KEY = new InputKeyboard();

    private boolean isProgramRunning = false;
	
	// Testing Stuff:
	private Effect[] effect = new Effect[1];
	// End Testing Stuff.
	
	public Screen() {
        JFrame frame = new JFrame();
		frame.setTitle("Particle Test");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);  
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.requestFocus();
		
		this.setSize(new Dimension(frame.getWidth(), frame.getHeight()));
        this.setFocusable(true);
        this.setVisible(true);
        this.setBackground(Color.black);
        this.addKeyListener(KEY);
		
		frame.add(this);
		frame.setVisible(true);
		
		//effect = new Snow(0.0, 0.0, frame.getWidth());
		for(int i=0;i<effect.length;i++) {
			effect[i] = new RainbowSnow(0.0, 0.0, 1920);
		}
		
		start();
	}

    public synchronized void start() {
        isProgramRunning = true;
        gameThread = new Thread(this, "Display");
        gameThread.start();
    }

	public void run() {
		// Keep looping until the program ends.
		while(isProgramRunning) {
			try {
                long startTime = System.currentTimeMillis();
                updateLogic(); // no delta needed
                render();
                long elapsedTime = System.currentTimeMillis() - startTime;
                Thread.sleep(elapsedTime);
            } catch(Exception e) {
                e.printStackTrace();
                continue;
            }
		}
		
		stop();
	}

	public synchronized void stop() {
		try {
			gameThread.join();
		} catch(InterruptedException e) {
			Logger.writeError(e.getMessage());
		}
	}

	// When called this updates all of the game's logic.
    // Still not entirely sure what to use delta for.
	public void updateLogic() {
		//((Snow)effect).update();
		for(Effect e : effect) {
            ((RainbowSnow)e).update();
		}
		
		if(KEY.isKeyPressed(KeyEvent.VK_ESCAPE) || KEY.isKeyPressed(KeyEvent.VK_ALT) && KEY.isKeyPressed(KeyEvent.VK_F4)) {
			isProgramRunning = false;
			System.exit(0);
		}
	}

	// When called this updates the screen.
	public void render() {
		// Forces the canvas to use triple buffering.
		BS = getBufferStrategy();
        if (BS == null) {
        	SwingUtilities.invokeLater(new Runnable() {
        	    public void run() {
        	        createBufferStrategy(3);
        	    }
        	});
        	return;
        }
		
        // Creates the graphics object and then clears the screen.
        Graphics g = BS.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());

        for(Effect e: effect) { ((RainbowSnow)e).render(g, e.getIsOval()); }
        
        g.dispose();
		BS.show();
	}
}
