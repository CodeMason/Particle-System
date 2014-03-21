package particle;

import java.awt.Color;
import java.awt.Graphics;

public class Particle {
	private double x, y;
	private double dx, dy; // Change in x and y.
	private double gravityX, gravityY; // The left/right and up/down gravity pull on the particle.
	private int size;
	private double currentLife, totalLife;
	private Color color;
	
	public Particle(double xIn, double yIn, double dxIn, double dyIn, double gravityXIn, double gravityYIn, int sizeIn, double lifeIn, Color colorIn) {
		x = xIn;
		y = yIn;
		dx = dxIn;
		dy = dyIn;
		gravityX = gravityXIn;
		gravityY = gravityYIn;
		size = sizeIn;
		currentLife = lifeIn;
		totalLife = lifeIn;
		color = colorIn;
	}
	
	public boolean update() {
		x += dx;
		y += dy;
		dx += gravityX;
		dy += gravityY;
		currentLife--;
		color = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)((currentLife/totalLife)*100));
		return (currentLife <= 0 ? true : false);
	}
	
	public void render(final Graphics G, final boolean IS_OVAL) {
		G.setColor(color);
		
		if(IS_OVAL) {
			G.fillOval((int)x-(size / 2), (int)y-(size / 2), size, size); //x-(size/2) & y-(size/2) make sure the particle is rendered at (x, y).
		} else {
			G.fillRect((int)x-(size / 2), (int)y-(size / 2), size, size); //x-(size/2) & y-(size/2) make sure the particle is rendered at (x, y).
		}

	}
}
