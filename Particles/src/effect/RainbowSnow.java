package effect;

import java.awt.Color;

import particle.Particle;

/**
 * Represents rainbow snow fall.
 * @author Valkryst
 * --- Last Edit 19-Mar-2014
 */
public class RainbowSnow extends Effect {
	/** The length (x-axis) of the screen. */
	private final double SCREEN_LENGTH;
	private double red = 255, green = 0, blue = 0;
	private boolean isRed = false, isGreen = true, isBlue = false;
	
	/**
	 * Constructs a new Snow object.
	 */
	public RainbowSnow(final double ORIGIN_X, final double ORIGIN_Y, final double SCREEN_LENGTH) {
		super(ORIGIN_X, ORIGIN_Y - 50, false);
		this.SCREEN_LENGTH = SCREEN_LENGTH;
	}
	
	/**
	 * Updates the snow.
	 */
	public void update() {
		if(counter == 10) {
			for(int i=0;i<RANDOM.nextInt(900) + 60;i++) { newParticle(new Color((int)red, (int)green, (int)blue, 100), RANDOM.nextInt(8), 40); }
			counter = 0;
		} else {
			counter++;
		}
		
		if(isRed) {
			if(blue > 0) {
				blue -= 0.25;
			}
			if(red < 255) {
				red += 0.25;
			} else {
				isRed = false;
				isGreen = true;
			}
		} else if(isGreen) {
			if(red > 0) {
				red -= 0.25;
			}
			if(green < 255) {
				green += 0.25;
			} else {
				isGreen = false;
				isBlue = true;
			}
		} else if(isBlue) {
			if(green > 0) {
				green -= 0.25;
			}
			if(blue < 255) {
				blue += 0.25;
			} else {
				isBlue = false;
				isRed = true;
			}
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
	public void newParticle(Color colorIn, int sizeIn, int decayTimeIn) {
		double x = RANDOM.nextInt((int)SCREEN_LENGTH), y = super.ORIGIN_Y;
		PARTICLES.add(new Particle(x, y, RANDOM.nextDouble() * (RANDOM.nextBoolean() ? -2 : 2), RANDOM.nextDouble() * 2.5, 0.0050 *(RANDOM.nextBoolean() ? -1 : 1), 0.0, sizeIn + RANDOM.nextInt(8), 150 + RANDOM.nextInt(800), colorIn));
	}
}
