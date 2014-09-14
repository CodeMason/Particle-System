package effect;

import particle.Particle;

import java.awt.*;

/**
 * Represents a fire.
 * @author Valkryst
 * --- Last Edit 10-May-2014
 */
public class Fire extends Effect {
    /** One of the four colors used by the fire particles. */
	private static final Color REDISH_BROWN = new Color(159, 70, 24, 100);
    /** One of the four colors used by the fire particles. */
	private static final Color DARK_ORANGE = new Color(208, 117, 29, 100);
    /** One of the four colors used by the fire particles. */
	private static final Color GOLD = new Color(246, 206, 72, 100);
    /** One of the four colors used by the fire particles. */
	private static final Color YELLOW = new Color(251, 239, 169, 100);

    /**
     * Constructs a new Snow particle effect.
     * @param ORIGIN_X The origin, on the X-axis, of the effect.
     * @param ORIGIN_Y The origin, on the Y-axis, of the effect.
     */
	public Fire(final double ORIGIN_X, final double ORIGIN_Y) {
		super(ORIGIN_X, ORIGIN_Y, false);
	}
	
	/**
     * Updates the fire particles by following two simple steps:
     *     If twenty update calls have been made, then create more particles.
     *     Call the update methods of all fire particles.
	 */
	public void update() {
        // After 20 update calls, create new particles.
        // This is an arbitrary number.
		if(counter == 20) {
			for(int i=0;i<10;i++) { newParticle(REDISH_BROWN, 150, 19); }
			for(int i=0;i<7;i++) { newParticle(DARK_ORANGE, 125, 16); }
			for(int i=0;i<5;i++) { newParticle(GOLD, 100, 13); }
			for(int i=0;i<2;i++) { newParticle(YELLOW, 50, 10); }
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
	public void newParticle(final Color COLOR, final int LIFE, final int MAX_AXIS_MOVEMENT) {
        boolean randBool = Math.random() >= 0.5;

        double xCoord = super.ORIGIN_X + (Math.random() * MAX_AXIS_MOVEMENT) * (randBool ? -1 : 1);
        double yCoord = super.ORIGIN_Y;
        double dx = Math.random() / 4;
        double dy = Math.random() * -1;
        double gravityX = 0.0;
        double gravityY = 0.0015 * (randBool ? 1 : -1);
        int size = 4 + (int)(Math.random() * 10);

		PARTICLES.add(new Particle(xCoord, yCoord, dx, dy, gravityX, gravityY, size, LIFE, COLOR));
	}
}
