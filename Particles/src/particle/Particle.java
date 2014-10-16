package particle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

/**
 * Represents a particle in 2D space.
 * @author Valkryst
 * --- Last Edit 13-October-2014
 */
public class Particle {
    /** The dimensions of the users screen. */
	private final static Dimension SCREEN_DIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();
	/** The currentlocation of the particle on the X-axis. */
	private float xCurrent;
	/** The current location of the particle on the Y-axis. */
	private float yCurrent;
    /** The previous location of the particle on the X-axis. */
    private float xPrevious;
    /** The previous location of the particle on the Y-axis. */
    private float yPrevious;
	/** The change in X, per update, of the particle. */
	private float dx;
	/** The change in Y, per update, of the particle. */
	private float dy;
	/** The gravitational pull to the left (negative) and right (positive) acting on this particle. */
	private final float gravityX;
	/** The gravitational pull to the up (negative) and down (positive) acting on this particle. */
	private final float gravityY;
	/** The size in pixels^2 of the particle. */
	private final byte SIZE;
	/** The remaining lifetime of the particle. */
	private float currentLife;
	/** The total lifetime of the particle. */
	private final short totalLife;
	/** The color of the particle. */
	private Color color;
	
	/**
	 * Constructs a new particle with the specified data.
	 * @param xCurrent The current location of the particle on the X-axis.
	 * @param yCurrent The currentlocation of the partivcle on the Y-axis.
	 * @param dx The change in X, per update, of the particle.
	 * @param dy The change in Y, per update, of the particle.
	 * @param gravityX The gravitational pull to the left (negative) and right (positive) acting on this particle.
	 * @param gravityY The gravitational pull to the up (negative) and down (positive) acting on this particle.
	 * @param size The size in pixels^2 of the particle.
	 * @param life The remaining lifetime of the particle.
	 * @param color The color of the particle.
	 */
	public Particle(final float xCurrent, final float yCurrent, final float dx, final float dy, final float gravityX, final float gravityY, final byte size, final short life, final Color color) {
		this.xCurrent = xCurrent;
		this.yCurrent = yCurrent;
        this.xPrevious = xCurrent;
        this.yPrevious = yCurrent;
		this.dx = dx;
		this.dy = dy;
		this.gravityX = gravityX;
		this.gravityY = gravityY;
		this.SIZE = size;
		currentLife = life;
		totalLife = life;
		this.color = color;
	}
	
	/**
     * If the particle is off-screen then return true. If true is returned then
     * the calling effect's update() method delete the particle.
     *
	 * Else updates the particle's position, change in xCurrent, change in yCurrent,
	 * remaining lifetime, and color.
	 * @return Whether the particle is 'dead' or not.
	 */
	public boolean update() {
		if(xCurrent > SCREEN_DIMENSIONS.width + 32 || xCurrent < -32 || yCurrent > SCREEN_DIMENSIONS.height + 32) {
			return true;
		} else {
            xPrevious = xCurrent;
            yPrevious = yCurrent;
			xCurrent += dx;
			yCurrent += dy;

			dx += gravityX;
			dy += gravityY;

			currentLife--;

            int alpha = (int)((currentLife/ totalLife)*100);
			color = new Color(color.getRed(), color.getGreen(), color.getBlue(), (alpha >= 0 ? alpha : 0));

			return currentLife <= 0;
		}
	}
	
	/**
	 * Renders the particle to the screen.
	 * @param g The graphics object to render with.
	 * @param isOval Whether or not to render the particle as an oval.
	 */
	public void render(final Graphics g, final boolean isOval) {
		g.setColor(color);

		if(isOval) {
			g.fillOval((int) xCurrent - (SIZE / 2), (int) yCurrent - (SIZE / 2), SIZE, SIZE); //xCurrent-(size/2) & yCurrent-(size/2) make sure the particle is rendered at (xCurrent, yCurrent).
		} else {
			g.fillRect((int) xCurrent - (SIZE / 2), (int) yCurrent - (SIZE / 2), SIZE, SIZE); //xCurrent-(size/2) & yCurrent-(size/2) make sure the particle is rendered at (xCurrent, yCurrent).
		}
	}
}
