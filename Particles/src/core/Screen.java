package core;

import effect.*;
import input.InputKeyboard;
import misc.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * Represents a screen on which to draw.
 * @author Valkryst
 * --- Last Edit 13-August-2014
 */
public class Screen extends Canvas implements Runnable {
    private Thread gameThread;

	private InputKeyboard KEY = new InputKeyboard();

    private boolean isProgramRunning = false;

	// Testing Stuff:
	private Effect[] effect = new Effect[3];
	// End Testing Stuff.

	public Screen() {
        this.addKeyListener(KEY);

        // Testing Stuff:
		    // effect[0] = new RainbowSnow(0.0, 0.0, 1920);
            effect[0] = new SplitWave(0.0, 0.0, 1920);
            // effect[0] = new Snow(0.0, 0.0, 1920);
            // effect[0] = new Fire(512.0, 512.0);
        // End Testing Stuff.
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
			    	update(delta);
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
	private void update(final double DELTA) {
        // Testing Stuff:
		for(Effect e : effect) {
            e.update();
		}
        // End Testing Stuff.

		if(KEY.isKeyPressed(KeyEvent.VK_ESCAPE) || KEY.isKeyPressed(KeyEvent.VK_ALT) && KEY.isKeyPressed(KeyEvent.VK_F4)) {
			isProgramRunning = false;
			System.exit(0);
		}
	}

	// When called this updates the screen.
	private void render() {
        // Forces the canvas to use triple buffering.
        // This can fail and cause an error on multi-monitor displays.
        BufferStrategy BS = getBufferStrategy();

        if(BS == null) {
            createBufferStrategy(3);
            BS = getBufferStrategy();
        }

        // Creates the graphics object and then clears the screen.
        Graphics g = BS.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());

        // Testing Stuff:
        for(Effect e: effect) {
            e.render(g, e.getIsOval());
        }
        // End Testing Stuff.
        
        g.dispose();
		BS.show();
	}
}
