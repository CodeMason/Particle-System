package effect;

import com.badlogic.gdx.graphics.Color;
import particle.Particle;
import particle.ParticleHole;

import java.awt.*;

/**
 * @author Valkryst
 * --- Last Edit 15-March-2015
 */
public class ParticleHoleEffect extends Effect {
    private final ParticleHole[] particleHoles = {
        new ParticleHole(250f, 250f, 0.000175f, 0.000075f),
        new ParticleHole(750f, 750f, -0.000075f, 0.000175f)
    };

    /** The total number of particles that this effect will use. */
    private static final short TOTAL_PARTICLES = 1000;


    /** An arbitrary number which controls how fast the rgb values are changed. */
    private static final float COLOR_CHANGE_CONSTANT = 0.00025f;
    /** An rgb value representing the color to apply to all new particles. */
    private float red = 1.0f, green = 0.0f, blue = 0.0f;
    /** A boolean value representing which color will be used next. */
    private boolean changingToRed = false, changingToGreen = true, changingToBlue = false;

    /**
     * Constructs a new ParticleHoleEffect particle effect.
     * @param screenDimensions The dimensions for the screen on which all particles are to be drawn.
     * @param originX The origin, on the X-axis, of the effect.
     * @param originY The origin, on the Y-axis, of the effect.
     */
    public ParticleHoleEffect(final Dimension screenDimensions, final float originX, final float originY) {
        super(screenDimensions, originX, originY + 50, false, TOTAL_PARTICLES);
    }

    /**
     * Constructs a new ParticleHoleEffect particle effect.
     * @param screenDimensions The dimensions for the screen on which all particles are to be drawn.
     * @param originX The origin, on the X-axis, of the effect.
     * @param originY The origin, on the Y-axis, of the effect.
     * @param totalParticles The total number of particles that this effect will use.
     */
    public ParticleHoleEffect(final Dimension screenDimensions, final float originX, final float originY, final short totalParticles) {
        super(screenDimensions, originX, originY + 50, false, totalParticles);
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
        // After 10 update calls, create new particles.
        // This is an arbitrary number, but it goes well with the
        // update() method of the Effect class because it's divisible
        // by 2.
        if(counter == 10) {
            for(short i=0;i<TOTAL_PARTICLES/10;i++) { newParticle(); }
            counter = 0;
        } else {
            counter++;
        }

        if(changingToRed) {
            if(blue > 0.0f) { blue -= COLOR_CHANGE_CONSTANT; }

            if(red < 1.0f) {
                red += COLOR_CHANGE_CONSTANT;
            } else if(red >= 1.0f && blue <= 0.0090f) {
                changingToRed = false;
                changingToGreen = true;
            }
        } else if(changingToGreen) {
            if(red > 0.0f) { red -= COLOR_CHANGE_CONSTANT; }

            if(green < 1.0f) {
                green += COLOR_CHANGE_CONSTANT;
            } else if(green >- 1.0f && red <= 0.0090f) {
                changingToGreen = false;
                changingToBlue = true;
            }
        } else if(changingToBlue) {
            if(green > 0.0f) { green -= COLOR_CHANGE_CONSTANT; }

            if(blue < 1.0f) {
                blue += COLOR_CHANGE_CONSTANT;
            } else if(blue >= 1.0f && green <= 0.0090f) {
                changingToBlue = false;
                changingToRed = true;
            }
        }

        // Apply gravitational pull of all of the ParticleHoles to all of the particles.
        for(Particle p : particles) {
            for(ParticleHole h : particleHoles) {
                h.applyGravitationalForceToParticle(p, screenDimensions);
            }
        }

        // Iterate through the list of particles and update each one.
        super.update();
    }

    /**
     * Creates a new Particle object.
     */
    public void newParticle() {
        short indexOfOpenPosition = super.canTakeNewParticles();

        if(indexOfOpenPosition != -1) {
            boolean randBool = Math.random() >= 0.5;
            float xCoord = (float)(Math.random() * super.screenDimensions.width);
            float yCoord = super.originY;
            float dx = (float)Math.random() * (randBool ? -2f : 2f);
            float dy = (float)Math.random() * -2.5f;
            byte size = (byte)(Math.random() * 16 + 1);
            short life = (short)(Math.random() * 800 + 1);
            Color color = new Color(red, green, blue, 1.0f);

            super.addParticle(indexOfOpenPosition, xCoord, yCoord, dx, dy, size, life, color);
        }
    }
}
