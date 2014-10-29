package effect;

import particle.Particle;

import java.awt.*;

/**
 * Represents a fire.
 * @author Valkryst
 * --- Last Edit 27-October-2014
 */
public class Fire extends Effect {
    /** The total number of particles that this effect will use. */
    private static final short TOTAL_PARTICLES = 200;

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
     * @param screenDimensions The dimensions for the screen on which all particles are to be drawn.
     * @param originX The origin, on the X-axis, of the effect.
     * @param originY The origin, on the Y-axis, of the effect.
     */
	public Fire(final Dimension screenDimensions, final float originX, final float originY) {
		super(screenDimensions, originX, originY, false, TOTAL_PARTICLES);
	}
	
	/**
     * Updates the fire particles by following two simple steps:
     *     If twenty update calls have been made, then create more particles.
     *     Call the update methods of all fire particles.
	 */
	public void update() {
        // After 20 update calls, create new particles.
        // This is an arbitrary number, but it goes well with the
        // update() method of the Effect class because it's divisible
        // by 2.
		if(counter == 20) {
            // Create X particles of each color. These numbers are all arbitrary.
			for(byte i=0;i<10;i++) { newParticle(REDISH_BROWN, (short)150, (short)19); }
			for(byte i=0;i<7;i++) { newParticle(DARK_ORANGE, (short)125, (short)16); }
			for(byte i=0;i<5;i++) { newParticle(GOLD, (short)100, (short)13); }
			for(byte i=0;i<2;i++) { newParticle(YELLOW, (short)50, (short)10); }
			counter = 0;
		} else {
			counter++;
		}

        // Iterate through the list of particles and update each one.
        super.update();
	}
	
	/**
	 * Creates a new Particle object.
	 * @param life The number of movements before the new Particle decays.
	 */
	public void newParticle(final Color color, final short life, final short maxAxisMovement) {
        short indexOfOpenPosition = super.canTakeNewParticles();

        if(indexOfOpenPosition != -1) {
            boolean randBool = Math.random() >= 0.5;

            float xCoord = super.originX + (float)(Math.random() * maxAxisMovement) * (randBool ? -1f : 1f);
            float yCoord = super.originY;
            float dx = (float)Math.random() / 4f;
            float dy = (float)Math.random() * -1f;
            float gravityX = 0.0f;
            float gravityY = 0.0015f * (randBool ? 1f : -1f);
            byte size = (byte)(4 + (Math.random() * 10));

            super.addParticle(new Particle(xCoord, yCoord, dx, dy, gravityX, gravityY, size, life, color), indexOfOpenPosition);
        }
	}
}
