package core;

import java.awt.Point;

/**
 * Represents a particle.
 * @author Valkryst
 * --- Last Edit 02-Mar-2014
 */
@SuppressWarnings("serial")
public class DoublePoint extends Point {
	/** The x coordinate of this Point. */
	private double x;
	/** The y coordinate of this Point. */
	private double y;
	
	/**
	 * Constructs a Point object.
	 * @param xIn The x coordinate of this Point.
	 * @param yIn The y coordinate of this Point.
	 */
	public DoublePoint(double xIn, double yIn) {
		x = xIn;
		y = yIn;
	}
	
	/** @return The x position of this Point. */
	public double getX() { return x; }
	/** @return The y position of this Point. */
	public double getY() { return y; }
	
	/** @param xIn The x coordinate to set to this Point. */
	public void setX(double xIn) { x = xIn; }
	/** @param yIn The y coordinate to set to this Point. */
	public void setY(double yIn) { y = yIn; }
}
