package effect;

import particle.Particle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a particle effect.
 * @author Valkryst
 * --- Last Edit 10-May-2014
 */
public class Effect {
	/** A collection of particles that make up the snow.*/
	protected final List<Particle> particles = new ArrayList<Particle>();
	/** The origin of this snow on the X-axis.*/
	protected final double originX;
	/** The origin of this snow on the Y-axis.*/
	protected final double originY;
	/** Whether or not to render the particles as ovals. If not then render as squares. Ovals are extremely CPU intensive for large effects*/
	protected final boolean isOval;
    /** Counts the number of update calls since the last creation of new particles. */
	protected int counter = 0;

    /**
     * Basic constructor for an effect.
     * @param originX The origin, on the X-axis, of the effect.
     * @param originY The origin, on the Y-axis, of the effect.
     * @param isOval Whether or not to render the particles as ovals. If not then they are rendered as squares.
     */
	public Effect(final double originX, final double originY, final boolean isOval) {
		this.originX = originX;
		this.originY = originY;
		this.isOval = isOval;
	}
	
	/**
	 * Updates each particle of the effect.
	 */
	public void update() {
        // Iterate through the list of particles and update each one.
        Iterator<Particle> iterator = particles.iterator();
        while(iterator.hasNext()) {
            if(iterator.next().update()) {
                iterator.remove();
            }
        }
    }
	
	/**
	 * Renders the snow to the screen.
	 * @param g Graphics object with which to draw.
	 */
	public void render(final Graphics g, final boolean isOval) {
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Iterator<Particle> iterator = particles.iterator();
		while(iterator.hasNext()) {
			iterator.next().render(g, isOval);
		}
	}
	
	/** @return Whether or not to render the particles as ovals. If not then render as squares. */
	public boolean getIsOval() { return isOval; }
}
