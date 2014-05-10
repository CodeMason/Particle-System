package effect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import particle.Particle;

/**
 * Represents a particle effect.
 * @author Valkryst
 * --- Last Edit 10-May-2014
 */
public class Effect {
	protected final static Random RANDOM = new Random();
	/** A collection of particles that make up the snow.*/
	protected final List<Particle> PARTICLES = new ArrayList<Particle>();
	/** The origin of this snow on the X-axis.*/
	protected final double ORIGIN_X;
	/** The origin of this snow on the Y-axis.*/
	protected final double ORIGIN_Y;
	/** Whether or not to render the particles as ovals. If not then render as squares. Ovals are extremely CPU intensive for large effects*/
	protected final boolean IS_OVAL;
    /** Used to iterate over the list which contains the particles. */
	protected Iterator<Particle> iterator;
    /** Counts the number of update calls since the last creation of new particles. */
	protected int counter = 0;

    /**
     * Basic constructor for an effect.
     * @param ORIGIN_X The origin, on the X-axis, of the effect.
     * @param ORIGIN_Y The origin, on the Y-axis, of the effect.
     * @param IS_OVAL Whether or not to render the particles as ovals. If not then they are rendered as squares.
     */
	public Effect(final double ORIGIN_X, final double ORIGIN_Y, final boolean IS_OVAL) {
		this.ORIGIN_X = ORIGIN_X;
		this.ORIGIN_Y = ORIGIN_Y;
		this.IS_OVAL = IS_OVAL;
	}
	
	/**
	 * Updates each particle of the effect.
	 */
	public void update() {
        // Iterate through the list of particles and update each one.
        iterator = PARTICLES.iterator();
        while(iterator.hasNext()) {
            if(iterator.next().update()) {
                iterator.remove();
            }
        }
    }
	
	/**
	 *  Renders the snow to the screen.
	 * @param G Graphics object with which to draw.
	 */
	public void render(final Graphics G, final boolean IS_OVAL) {
		((Graphics2D)G).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		iterator = PARTICLES.iterator();
		while(iterator.hasNext()) {
			iterator.next().render(G, IS_OVAL);
		}
	}
	
	/** @return Whether or not to render the particles as ovals. If not then render as squares. */
	public boolean getIsOval() { return IS_OVAL; } 
}
