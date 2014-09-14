package effect;

import particle.Particle;

import java.awt.*;

/**
 * Represents snow fall.
 * @author Valkryst
 * --- Last Edit 10-May-2014
 */
public class Snow extends Effect {
	/** The length (x-axis) of the screen. */
	private final double SCREEN_LENGTH;

    /**
     * Constructs a new Snow particle effect.
     * @param ORIGIN_X The origin, on the X-axis, of the effect.
     * @param ORIGIN_Y The origin, on the Y-axis, of the effect.
     * @param SCREEN_LENGTH The total length of the screen.
     */
	public Snow(final double ORIGIN_X, final double ORIGIN_Y, final double SCREEN_LENGTH) {
		super(ORIGIN_X, ORIGIN_Y - 50, true);
		this.SCREEN_LENGTH = SCREEN_LENGTH;
	}

    /**
     * Updates the snow particles by following two simple steps:
     *     If ten update calls have been made, then create more particles.
     *     Call the update methods of all snow particles.
     */
	public void update() {
        // After 10 update calls, create new particles.
        // This is an arbitrary number.
		if(counter == 10) {
			for(int i=0;i<(Math.random() * 300 + 1) + 20;i++) { newParticle(40); }
			counter = 0;
		} else {
			counter++;
		}

       // Iterate through the list of particles and update each one.
       super.update();
	}
	
	/**
	 * Creates a new Particle object.
	 * @param LIFE The number of movements before the new Particle decays.
	 */
	public void newParticle(final int LIFE) {
        double xCoord = (int)(Math.random() * SCREEN_LENGTH);
        double yCoord = super.ORIGIN_Y;
        double dx = Math.random() * (Math.random() >= 0.5 ? -2 : 2);
        double dy = Math.random() * 2.5;
        double gravityX = -0.0075;
        double gravityY = 0.0;
        int size = (int)(Math.random() * 8 + 1);
        double life = LIFE + (Math.random() * 800 + 1);
        PARTICLES.add(new Particle(xCoord, yCoord, dx, dy, gravityX, gravityY, size, life, Color.WHITE));
	}
}
