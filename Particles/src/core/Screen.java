package core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import effect.Effect;
import effect.Fire;

import java.awt.*;

/**
 * Represents a screen on which to draw.
 * @author Valkryst
 * --- Last Edit 29-January-2015
 */
public class Screen implements ApplicationListener {
    private OrthographicCamera camera;
    private ShapeRenderer renderer;
    private Effect effect;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 512);

        renderer = new ShapeRenderer();

        effect = new Fire(new Dimension(912, 512), 256f, 256f);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        effect.setScreenDimensions(new Dimension(width, height), width/2, height/2);
    }

    @Override
    public void render() {
        Gdx.graphics.getGL30().glClearColor(0, 0, 0, 1);
        Gdx.graphics.getGL30().glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);

        Gdx.graphics.getGL30().glEnable(GL30.GL_BLEND);
        Gdx.graphics.getGL30().glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);

        camera.update();
        effect.update();

        renderer.setProjectionMatrix(camera.combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        effect.render(renderer);
        renderer.end();
    }

    /*
    private Thread gameThread;

	private InputKeyboard KEY = new InputKeyboard();

    private boolean isProgramRunning = false;

	// Testing Stuff:
	private Effect[] effect = new Effect[1];
    private Dimension previousScreenDimensions;
	// End Testing Stuff.

	public Screen() {
        this.addKeyListener(KEY);
	}

    // Testing Stuff:
    public void setEffect(final Dimension screenDimensions) {
        effect[0] = new RainbowSnow(screenDimensions, 0.0f, 0.0f);
    }
    // End Testing Stuff.

    /** Start the render-update-loop thread.
    public synchronized void start() {
        previousScreenDimensions = this.getSize();
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
			    	update();
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

    /** Stop the render-update-loop thread.
	public synchronized void stop() {
		try {
			gameThread.join();
		} catch(InterruptedException e) {
			Logger.writeLog(e.getMessage(), Logger.LOG_TYPE_ERROR);
		}
	}

	// When called this updates all of the game's logic.
    // Still not entirely sure what to use delta for.
	private void update() {
        // Testing Stuff:
        if(KEY.isKeyPressed(KeyEvent.VK_F1)) {
            effect[0] = new RainbowSnow(this.getSize(), 0.0f, 0.0f);
        } else if(KEY.isKeyPressed(KeyEvent.VK_F2)) {
            effect[0] = new SplitWave(this.getSize(), 0.0f, 0.0f);
        } else if(KEY.isKeyPressed(KeyEvent.VK_F3)) {
            effect[0] = new Snow(this.getSize(), 0.0f, 0.0f);
        } else if(KEY.isKeyPressed(KeyEvent.VK_F4)) {
            effect[0] = new Fire(this.getSize(), (float)this.getWidth()/2, (float)this.getHeight()/2);
        } else if(KEY.isKeyPressed(KeyEvent.VK_F5)) {
            effect[0] = new ParticleHoleEffect(this.getSize(), 0.0f, 0.0f);
        }

        if(!this.getSize().equals(previousScreenDimensions)) {
            previousScreenDimensions = this.getSize();
            for(Effect e : effect) {
                e.setScreenDimensions(this.getSize());
            }
        }

		for(Effect e : effect) {
            e.update();
		}
        // End Testing Stuff

		if(KEY.isKeyPressed(KeyEvent.VK_ESCAPE) || KEY.isKeyPressed(KeyEvent.VK_ALT) && KEY.isKeyPressed(KeyEvent.VK_F4)) {
			isProgramRunning = false;
			System.exit(0);
		}
	}

	// When called this updates the screen.
	private void render() {
        BufferStrategy BS = getBufferStrategy();

        // Forces the canvas to use triple buffering.
        // This can fail and cause an exception on multi-monitor displays.
        if(BS == null) {
            createBufferStrategy(3);
            BS = getBufferStrategy();
        }

        // Creates the graphics object and then clears the screen.
        Graphics g = BS.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());

        for(Effect e : effect) {
            e.render(g);
        }

        g.dispose();
        BS.show();
	}
    */
}
