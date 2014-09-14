package effect;

import particle.Particle;

import java.awt.*;

/**
 * Represents rainbow snow fall.
 * @author Valkryst
 * --- Last Edit 10-August-2014
 */
public class RainbowSnow extends Effect {
	/** The length (x-axis) of the screen. */
	private final double SCREEN_LENGTH;
    /** An arbitrary number which controls how fast the rgb values are changed. */
	private final double COLOR_CHANGE_CONSTANT = 0.250;
    /** An rgb value representing the color to apply to all new particles. */
	private double red = 255, green = 0, blue = 0;
    /** A boolean value representing which color will be used next. */
	private boolean changingToRed = false, changingToGreen = true, changingToBlue = false;

    /**
     * Constructs a new RwinbowSnow particle effect.
     * @param ORIGIN_X The origin, on the X-axis, of the effect.
     * @param ORIGIN_Y The origin, on the Y-axis, of the effect.
     * @param SCREEN_LENGTH The total length of the screen.
     */
	public RainbowSnow(final double ORIGIN_X, final double ORIGIN_Y, final double SCREEN_LENGTH) {
		super(ORIGIN_X, ORIGIN_Y - 50, true);
		this.SCREEN_LENGTH = SCREEN_LENGTH;
	}
	
	/**
	 * Creates a new color using the current rgb values.
     * Initializes new snow particles.
     * Changes the rgb values in the following way:
     *     If we use the default values of red = 255 & changingToGreen = true then...
     *         If red > 0 & green < 255
     *         then red -= COLOR_CHANGE_CONSTANT & green += COLOR_CHANGE_CONSTANT.
     *
     *         Then when red == 0 and green == 255
     *         changingToGreen = false & changingToBlue = true
     *
     *         Now repeat from the beginning, but swap the word red for green
     *         and green for blue. Then when you reach the end swap the word
     *         green for blue and blue for red. Thus the colors cycle.
     *
	 */
	public void update() {
		if(counter == 10) {
			for(int i=0;i<(Math.random() * 1800 + 1) + 120;i++) { newParticle(); }
			counter = 0;
		} else {
			counter++;
		}

		if(changingToRed) {
			if(blue > 0) { blue -= COLOR_CHANGE_CONSTANT; }

			if(red < 255) {
				red += COLOR_CHANGE_CONSTANT;
			} else if(red == 255 && blue == 0) {
                changingToRed = false;
                changingToGreen = true;
			}
		} else if(changingToGreen) {
			if(red > 0) { red -= COLOR_CHANGE_CONSTANT; }

			if(green < 255) {
				green += COLOR_CHANGE_CONSTANT;
			} else if(green == 255 && red == 0) {
                changingToGreen = false;
                changingToBlue = true;
			}
		} else if(changingToBlue) {
			if(green > 0) { green -= COLOR_CHANGE_CONSTANT; }

			if(blue < 255) {
				blue += COLOR_CHANGE_CONSTANT;
			} else if(blue == 255 && green == 0) {
                changingToBlue = false;
                changingToRed = true;
			}
		}

        // Iterate through the list of particles and update each one.
        super.update();
	}
	
	/**
	 * Creates a new Particle object.
	 */
	public void newParticle() {
        boolean randBool = Math.random() >= 0.5;
        double xCoord = (int)(Math.random() * SCREEN_LENGTH);
        double yCoord = super.ORIGIN_Y;
        double dx = Math.random() * (randBool ? -2 : 2);
        double dy = Math.random() * 2.5;
        double gravityX = 0.0050 * (randBool ? -1 : 1);
        double gravityY = 0.0;
        int size = (int)(Math.random() * 16 + 1);
        double life = (Math.random() * 800 + 1);
        Color color = new Color((int)red, (int)green, (int)blue, 100);

        PARTICLES.add(new Particle(xCoord, yCoord, dx, dy, gravityX, gravityY, size, life, color));
	}
}
