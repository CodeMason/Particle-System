package effect;

import particle.Particle;

import java.awt.*;

/**
 * Represents rainbow snow fall.
 * @author Valkryst
 * --- Last Edit13-October-2014
 */
public class SplitWave extends Effect {
    /** The total number of particles that this effect will use. */
    private static final short TOTAL_PARTICLES = 10000;

    /** The length (x-axis) of the screen. */
    private final double screenLength;
    /** An arbitrary number which controls how fast the rgb values are changed. */
    private static final double COLOR_CHANGE_CONSTANT = 3;
    /** An rgb value representing the color to apply to all new particles. */
    private double red = 255, green = 0, blue = 0;
    /** A boolean value representing which color will be used next. */
    private boolean changingToRed = false, changingToGreen = true, changingToBlue = false;

    /**
     * Constructs a new SplitWave particle effect.
     * @param originX The origin, on the X-axis, of the effect.
     * @param originY The origin, on the Y-axis, of the effect.
     * @param screenLength The total length of the screen.
     */
    public SplitWave(final double originX, final double originY, final double screenLength) {
        super(originX, originY - 50, true, TOTAL_PARTICLES);
        this.screenLength = screenLength;
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
        Color c = new Color((int)red, (int)green, (int)blue, 100);

        // After 10 update calls, create new particles.
        // This is an arbitrary number, but it goes well with the
        // update() method of the Effect class because it's divisible
        // by 2.
        if(counter == 10) {
            for(int i=0;i<TOTAL_PARTICLES/100;i++) { newParticle(c, (int)(Math.floor(Math.random() * 6) + 8), 40); }
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
     * @param color The color of the new particle
     * @param size The size, in pixels^2, of the new Particle.
     * @param life The number of movements before the new Particle decays.
     */
    public void newParticle(final Color color, int size, int life) {
        short indexOfOpenPosition = super.canTakeNewParticles();

        if(indexOfOpenPosition != -1) {
            boolean randBool = Math.random() >= 0.5;
            double randFloat = Math.random();

            super.addParticle(new Particle((int) (randFloat * screenLength + 1), super.originY, randFloat * (randBool ? -2 : 2), randFloat * 2.5, 0.0050 * (randBool ? -1 : 1), 0.0, size + (int) (randFloat * 8 + 1), life + (int) (Math.random() * 800 + 1), color), indexOfOpenPosition);
        }
    }
}

