package effect;

import java.awt.Color;

import particle.Particle;

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
			for(int i=0;i<10;i++) { newParticle(REDISH_BROWN, 4, 150, 19); }
			for(int i=0;i<7;i++) { newParticle(DARK_ORANGE, 4, 125, 16); }
			for(int i=0;i<5;i++) { newParticle(GOLD, 4, 100, 13); }
			for(int i=0;i<2;i++) { newParticle(YELLOW, 4, 50, 10); }
			counter = 0;
		} else {
			counter++;
		}

        // Iterate through the list of particles and update each one.
		iterator = PARTICLES.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().update()) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * Creates a new Particle object.
	 * @param SIZE The size, in pixels^2, of the new Particle.
	 * @param LIFE The number of movements before the new Particle decays.
	 */
	public void newParticle(final Color COLOR, final int SIZE, final int LIFE, final int MAX_AXIS_MOVEMENT) {
		double x = super.ORIGIN_X;
		x += RANDOM.nextInt(MAX_AXIS_MOVEMENT) * (RANDOM.nextBoolean() ? -1 : 1);
		PARTICLES.add(new Particle(x, super.ORIGIN_Y, RANDOM.nextDouble() / 4, RANDOM.nextDouble() * -1, 0.0, 0.0015 * (RANDOM.nextBoolean() ? 1 : -1), SIZE + RANDOM.nextInt(10), LIFE, COLOR));
	}
}
