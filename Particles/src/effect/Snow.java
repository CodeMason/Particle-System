package effect;

import java.awt.Color;

import particle.Particle;

/**
 * Represents snow fall.
 * @author Valkryst
 * --- Last Edit 14-Mar-2014
 */
public class Snow extends Effect {
	/** The length (x-axis) of the screen. */
	private final double SCREEN_LENGTH;
	
	/**
	 * Constructs a new Snow object.
	 */
	public Snow(final double ORIGIN_X, final double ORIGIN_Y, final double SCREEN_LENGTH) {
		super(ORIGIN_X, ORIGIN_Y - 50, true);
		this.SCREEN_LENGTH = SCREEN_LENGTH;
	}
	
	/**
	 * Updates the snow.
	 */
	public void update() {
		if(counter == 10) {
			for(int i=0;i<RANDOM.nextInt(300) + 20;i++) { newParticle(RANDOM.nextInt(8), 40); }
			counter = 0;
		} else {
			counter++;
		}

		iterator = PARTICLES.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().update()) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * Creates a new Particle object.
	 * @param sizeIn The size, in pixels^2, of the new Particle.
	 * @param decayTimeIn The number of movements before the new Particle decays.
	 */
	public void newParticle(int sizeIn, int decayTimeIn) {
		double x = RANDOM.nextInt((int)SCREEN_LENGTH), y = super.ORIGIN_Y;
		PARTICLES.add(new Particle(x, y, RANDOM.nextDouble() * (RANDOM.nextBoolean() ? -2 : 2), RANDOM.nextDouble() * 2.5, -0.0075, 0.0, sizeIn, 150 + RANDOM.nextInt(800), Color.WHITE));
	}
}
