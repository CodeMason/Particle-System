package effect;

import particle.Particle;

import java.awt.*;

/**
 * Represents a particle effect.
 * @author Valkryst
 * --- Last Edit 13-October-2014
 */
public class Effect {
	/** A collection of particles that make up the snow.*/
	protected final Particle[] particles;
	/** The origin of this snow on the X-axis.*/
	protected final float originX;
	/** The origin of this snow on the Y-axis.*/
	protected final float originY;
	/** Whether or not to render the particles as ovals. If not then render as squares. Ovals are extremely CPU intensive for large effects*/
	protected final boolean isOval;
    /** Counts the number of update calls since the last creation of new particles. */
	protected byte counter = 0;

    /**
     * Basic constructor for an effect.
     * @param originX The origin, on the X-axis, of the effect.
     * @param originY The origin, on the Y-axis, of the effect.
     * @param isOval Whether or not to render the particles as ovals. If not then they are rendered as squares.
     * @param totalParticles The total number of particles that this effect will be allowed to use.
     */
	public Effect(final float originX, final float originY, final boolean isOval, final short totalParticles) {
		this.originX = originX;
		this.originY = originY;
		this.isOval = isOval;
        particles = new Particle[totalParticles];
	}
	
	/**
	 * Updates half of all of the particles of the effect.
     * On the first call, the particles from 0 to (particles.length/2) are updated.
     * On the second call, the partuicles from ((particles.length/2)+1) to particles.length are updated.
     *
     * This algorithm of only updating half of the particles at any given time should, in theory, reduce the
     * resources used on every update call. The alternative is to update every single particle on every single update
     * call which happens multiple times a second.
	 */
	public void update() {
        // Initalize variables to a default and then set them based on certain conditions.
        short startingIndex = 0;
        short endingIndex = (short)particles.length;

        switch(counter % 4) { // When the counter is equal to zero, neither of these will be run, but it's not noticable.
            case 1: {
                endingIndex = (short)(particles.length / 2);
                break;
            }
            case 2: {
                startingIndex = (short)((particles.length / 2) + 1);
                endingIndex = (short)particles.length;
                break;
            }
        }

        // Update the specified set of particles.
        for(short i=startingIndex;i<endingIndex;i++) {
            if(particles[i] != null) {
                if(particles[i].update()) {
                    particles[i] = null;
                }
            }
        }
    }
	
	/**
	 * Renders the snow to the screen.
	 * @param g Graphics object with which to draw.
	 */
	public void render(final Graphics g) {
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for(Particle p : particles) {
            if(p != null) {
                p.render(g, isOval);
            }
        }
	}

    public short canTakeNewParticles() {
        for(short i=0;i<particles.length;i++) {
            if(particles[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public void addParticle(final Particle newParticle, final short index) {
        particles[index] = newParticle;
    }
	
	/** @return Whether or not to render the particles as ovals. If not then render as squares. */
	public boolean getIsOval() { return isOval; }
}
