package effect;

import java.awt.Color;

import particle.Particle;

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
			for(int i=0;i<RANDOM.nextInt(300) + 20;i++) { newParticle(RANDOM.nextInt(8), 40); }
			counter = 0;
		} else {
			counter++;
		}

       // Iterate through the list of particles and update each one.
       super.update();
	}
	
	/**
	 * Creates a new Particle object.
	 * @param SIZE The size, in pixels^2, of the new Particle.
	 * @param LIFE The number of movements before the new Particle decays.
	 */
	public void newParticle(final int SIZE, final int LIFE) {
		PARTICLES.add(new Particle(RANDOM.nextInt((int)SCREEN_LENGTH), super.ORIGIN_Y, RANDOM.nextDouble() * (RANDOM.nextBoolean() ? -2 : 2), RANDOM.nextDouble() * 2.5, -0.0075, 0.0, SIZE, LIFE + RANDOM.nextInt(800), Color.WHITE));
	}
}
