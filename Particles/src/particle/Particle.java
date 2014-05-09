package particle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

/**
 * Represents a particle in 2D space.
 * @author Valkryst
 * --- Last Edit 21-Mar-2014
 */
public class Particle {
	private final static Dimension SCREEN_DIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();
	/** The location of the particle on the X-axis. */
	private double x;
	/** The location of the particle on the Y-axis. */
	private double y;
	/** The change in X, per update, of the particle. */
	private double dx;
	/** The change in Y, per update, of the particle. */
	private double dy;
	/** The gravitational pull to the left (negative) and right (positive) acting on this particle. */
	private final double GRAVITY_X;
	/** The gravitational pull to the up (negative) and down (positive) acting on this particle. */
	private final double GRAVITY_Y;
	/** The size in pixels^2 of the particle. */
	private final int SIZE;
	/** The remaining lifetime of the particle. */
	private double currentLife;
	/** The total lifetime of the particle. */
	private final double TOTAL_LIFE;
	/** The color of the particle. */
	private Color color;
	
	/**
	 * Constructs a new particle with the specified data.
	 * @param X The location of the particle on the X-axis.
	 * @param Y The location of the partivcle on the Y-axis.
	 * @param DX The change in X, per update, of the particle.
	 * @param DY The change in Y, per update, of the particle.
	 * @param GRAVITY_X The gravitational pull to the left (negative) and right (positive) acting on this particle.
	 * @param GRAVITY_Y The gravitational pull to the up (negative) and down (positive) acting on this particle.
	 * @param SIZE The size in pixels^2 of the particle.
	 * @param LIFE The remaining lifetime of the particle.
	 * @param COLOR The color of the particle.
	 */
	public Particle(final double X, final double Y, final double DX, final double DY, final double GRAVITY_X, final double GRAVITY_Y, final int SIZE, final double LIFE, final Color COLOR) {
		x = X;
		y = Y;
		dx = DX;
		dy = DY;
		this.GRAVITY_X = GRAVITY_X;
		this.GRAVITY_Y = GRAVITY_Y;
		this.SIZE = SIZE;
		currentLife = LIFE;
		TOTAL_LIFE = LIFE;
		color = COLOR;
	}
	
	/**
	 * Updates the particle's position, change in x, change in y,
	 * remaining lifetime, and color.
	 * @return Whether the particle is 'dead' or not.
	 */
	public boolean update() {
		if(x > SCREEN_DIMENSIONS.width + 32 || x < -32 || y > SCREEN_DIMENSIONS.height + 32) {
			return true;
		} else {
			x += dx;
			y += dy;
			dx += GRAVITY_X;
			dy += GRAVITY_Y;
			currentLife--;
			color = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)((currentLife/TOTAL_LIFE)*100));
			return currentLife <= 0;
		}
	}
	
	/**
	 * Renders the particle to the screen.
	 * @param G The graphics object to render with.
	 * @param IS_OVAL Whether or not to render the particle as an oval.
	 */
	public void render(final Graphics G, final boolean IS_OVAL) {
		G.setColor(color);

		if(IS_OVAL) {
			G.fillOval((int)x-(SIZE / 2), (int)y-(SIZE / 2), SIZE, SIZE); //x-(size/2) & y-(size/2) make sure the particle is rendered at (x, y).
		} else {
			G.fillRect((int)x-(SIZE / 2), (int)y-(SIZE / 2), SIZE, SIZE); //x-(size/2) & y-(size/2) make sure the particle is rendered at (x, y).
		}
	}
}
