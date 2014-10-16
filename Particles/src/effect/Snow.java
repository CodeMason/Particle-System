package effect;

import particle.Particle;

import java.awt.*;

/**
 * Represents snow fall.
 * @author Valkryst
 * --- Last Edit 13-October-2014
 */
public class Snow extends Effect {
    /** The total number of particles that this effect will use. */
    private static final short TOTAL_PARTICLES = 5000;

	/** The length (x-axis) of the screen. */
	private final short screenLength;

    /**
     * Constructs a new Snow particle effect.
     * @param originX The origin, on the X-axis, of the effect.
     * @param originY The origin, on the Y-axis, of the effect.
     * @param screenLength The total length of the screen.
     */
	public Snow(final float originX, final float originY, final short screenLength) {
		super(originX, originY - 50, true, TOTAL_PARTICLES);
		this.screenLength = screenLength;
	}

    /**
     * Constructs a new Snow particle effect.
     * @param originX The origin, on the X-axis, of the effect.
     * @param originY The origin, on the Y-axis, of the effect.
     * @param screenLength The total length of the screen.
     * @param totalParticles The total number of particles that this effect will use.
     */
    public Snow(final float originX, final float originY, final short screenLength, final short totalParticles) {
        super(originX, originY - 50, true, totalParticles);
        this.screenLength = screenLength;
    }

    /**
     * Updates the snow particles by following two simple steps:
     *     If ten update calls have been made, then create more particles.
     *     Call the update methods of all snow particles.
     */
	public void update() {
        // After 10 update calls, create new particles.
        // This is an arbitrary number, but it goes well with the
        // update() method of the Effect class because it's divisible
        // by 2.
		if(counter == 10) {
			for(int i=0;i<TOTAL_PARTICLES/100;i++) { newParticle(); }
			counter = 0;
		} else {
			counter++;
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
            float xCoord = (float)(Math.random() * screenLength);
            float yCoord = super.originY;
            float dx = (float)Math.random() * (Math.random() >= 0.5f ? -2f : 2f);
            float dy = (float)Math.random() * 2.5f;
            float gravityX = -0.0075f;
            float gravityY = 0.0f;
            byte size = (byte)(Math.random() * 8 + 1);
            short life = (short)(Math.random() * 800 + 1);

            super.addParticle(new Particle(xCoord, yCoord, dx, dy, gravityX, gravityY, size, life, Color.WHITE), indexOfOpenPosition);
        }
	}
}
