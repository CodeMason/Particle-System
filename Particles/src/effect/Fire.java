package effect;

import java.awt.Color;

import particle.Particle;

/**
 * Represents a fire.
 * @author Valkryst
 * --- Last Edit 14-Mar-2014
 */
public class Fire extends Effect {
	private static final Color ONE = new Color(159, 70, 24, 100);
	private static final Color TWO = new Color(208, 117, 29, 100);
	private static final Color THREE = new Color(246, 206, 72, 100);
	private static final Color FOUR = new Color(251, 239, 169, 100);
	
	/**
	 * Constructs a new Fire object.
	 */
	public Fire(final double ORIGIN_X, final double ORIGIN_Y) {
		super(ORIGIN_X, ORIGIN_Y, false);
	}
	
	/**
	 * Updates the fire.
	 */
	public void update() {
		if(counter == 20) {
			for(int i=0;i<10;i++) { newParticle(ONE, 4, 150, 19); }
			for(int i=0;i<7;i++) { newParticle(TWO, 4, 125, 16); }
			for(int i=0;i<5;i++) { newParticle(THREE, 4, 100, 13); }
			for(int i=0;i<2;i++) { newParticle(FOUR, 4, 50, 10); }
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
	
	public void newParticle(Color colorIn, int sizeIn, int decayTimeIn, int maximumXAxisMovement) {
		double x = super.ORIGIN_X, y = super.ORIGIN_Y;
		x += RANDOM.nextInt(maximumXAxisMovement) * (RANDOM.nextBoolean() ? -1 : 1);
		PARTICLES.add(new Particle(x, y, RANDOM.nextDouble() / 4, RANDOM.nextDouble() * -1, 0.0, 0.0015 * (RANDOM.nextBoolean() ? 1 : -1), sizeIn + RANDOM.nextInt(10), decayTimeIn, colorIn));
	}
}
