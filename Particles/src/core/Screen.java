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
 * --- Last Edit 10-August-2014
 */
public class Screen extends Canvas implements Runnable {
    private Thread gameThread;

	private BufferStrategy BS = getBufferStrategy();
	private InputKeyboard KEY = new InputKeyboard();

    private boolean isProgramRunning = false;

	// Testing Stuff:
	private Effect[] effect = new Effect[1];
	// End Testing Stuff.

	public Screen(final Effect[] EFFECTS) {
        this.addKeyListener(KEY);

        // Testing Stuff:
		//effect = new Snow(0.0, 0.0, frame.getWidth());
		for(int i=0;i<effect.length;i++) {
			effect[i] = new RainbowSnow(0.0, 0.0, 1920);
		}
        // End Testing Stuff.

		start();
	}

    public synchronized void start() {
        isProgramRunning = true;
        gameThread = new Thread(this, "Display");
        gameThread.start();
    }

	public void run() {
		long lastLoopTime = System.nanoTime();
	    final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		double delta = 0;

		// Keep looping until the program ends.
		while(isProgramRunning) {
				long now = System.nanoTime();
				long updateLength = now - lastLoopTime;
				lastLoopTime = now;
			    delta += updateLength / ((double)OPTIMAL_TIME); // Work out how long its been since the last update.

			    //Update the game's logic and then render the screen.
			    while(delta >= 1) {
			    	updateLogic(delta);
			    	delta--;
			    }

			    render();

			    // we want each frame to take 10 milliseconds, to do this
			    // we've recorded when we started the frame. We add 10 milliseconds
			    // to this and then factor in the current time to give
			    // us our final value to wait for
			    // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
			    try {
			    	long tempLong = (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000;
			    	if(tempLong <= 0) { continue; } // Skips the sleep()
					Thread.sleep(tempLong);
				} catch (InterruptedException e) {
					continue;
				}
		}

		stop();
	}

	public synchronized void stop() {
		try {
			gameThread.join();
		} catch(InterruptedException e) {
			Logger.writeLog(e.getMessage(), Logger.LOG_TYPE_ERROR);
		}
	}

	// When called this updates all of the game's logic.
    // Still not entirely sure what to use delta for.
	public void updateLogic(final double DELTA) {
        // Testing Stuff:
		//((Snow)effect).update();
		for(Effect e : effect) {
            ((RainbowSnow)e).update();
		}
        // End Testing Stuff.

		if(KEY.isKeyPressed(KeyEvent.VK_ESCAPE) || KEY.isKeyPressed(KeyEvent.VK_ALT) && KEY.isKeyPressed(KeyEvent.VK_F4)) {
			isProgramRunning = false;
			System.exit(0);
		}
	}

	// When called this updates the screen.
	public void render() {
		// Forces the canvas to use triple buffering.
        // This can fail and cause an error on multi-monitor displays.
		BS = getBufferStrategy();
        if(BS == null) {
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

        // Testing Stuff:
        for(Effect e: effect) { ((RainbowSnow)e).render(g, e.getIsOval()); }
        // End Testing Stuff.
        
        g.dispose();
		BS.show();
	}
}
