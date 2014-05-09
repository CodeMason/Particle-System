package effect;

import java.awt.Color;

import particle.Particle;

/**
 * Represents rainbow snow fall.
 * @author Valkryst
 * --- Last Edit 9-May-2014
 */
public class RainbowSnow extends Effect {
	/** The length (x-axis) of the screen. */
	private final double SCREEN_LENGTH;
	private final double COLOR_CHANGE_CONSTANT = 0.125;
	private double red = 255, green = 0, blue = 0;
	private boolean isRed = false, isGreen = true, isBlue = false;
	
	/**
	 * Constructs a new Snow object.
	 */
	public RainbowSnow(final double ORIGIN_X, final double ORIGIN_Y, final double SCREEN_LENGTH) {
		super(ORIGIN_X, ORIGIN_Y - 50, true);
		this.SCREEN_LENGTH = SCREEN_LENGTH;
	}
	
	/**
	 * Updates the snow.
	 */
	public void update() {
		Color c = new Color((int)red, (int)green, (int)blue, 100);
		if(counter == 10) {
			for(int i=0;i<RANDOM.nextInt(1800) + 120;i++) { newParticle(c, RANDOM.nextInt(8), 40); }
			counter = 0;
		} else {
			counter++;
		}

		if(isRed) {
			if(blue > 0) { blue -= COLOR_CHANGE_CONSTANT; }

			if(red < 255) {
				red += COLOR_CHANGE_CONSTANT;
			} else if(red == 255 && blue == 0) {
				isRed = false;
				isGreen = true;
			}
		} else if(isGreen) {
			if(red > 0) { red -= COLOR_CHANGE_CONSTANT; }

			if(green < 255) {
				green += COLOR_CHANGE_CONSTANT;
			} else if(green == 255 && red == 0) {
				isGreen = false;
				isBlue = true;
			}
		} else if(isBlue) {
			if(green > 0) { green -= COLOR_CHANGE_CONSTANT; }

			if(blue < 255) {
				blue += COLOR_CHANGE_CONSTANT;
			} else if(blue == 255 && green == 0) {
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
     * @param COLOR The color of the new particle
	 * @param SIZE The size, in pixels^2, of the new Particle.
	 * @param LIFE The number of movements before the new Particle decays.
	 */
	public void newParticle(final Color COLOR, int SIZE, int LIFE) {
		PARTICLES.add(new Particle(RANDOM.nextInt((int)SCREEN_LENGTH), super.ORIGIN_Y, RANDOM.nextDouble() * (RANDOM.nextBoolean() ? -2 : 2), RANDOM.nextDouble() * 2.5, 0.0050 *(RANDOM.nextBoolean() ? -1 : 1), 0.0, SIZE + RANDOM.nextInt(8), LIFE + RANDOM.nextInt(800), COLOR));
	}
}
