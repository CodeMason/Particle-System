package particle;

import java.awt.Color;
import java.awt.Graphics;

public class Particle {
	private double x, y;
	private double dx, dy; // Change in x and y.
	private final double GRAVITY_X, GRAVITY_Y; // The left/right and up/down gravity pull on the particle.
	private final int size;
	private double currentLife, totalLife;
	private Color color;
	
	public Particle(final double X, final double Y, final double DX, final double DY, final double GRAVITY_X, final double GRAVITY_Y, final int SIZE, final double LIFE, final Color COLOR) {
		x = X;
		y = Y;
		dx = DX;
		dy = DY;
		this.GRAVITY_X = GRAVITY_X;
		this.GRAVITY_Y = GRAVITY_Y;
		size = SIZE;
		currentLife = LIFE;
		totalLife = LIFE;
		color = COLOR;
	}
	
	public boolean update() {
		x += dx;
		y += dy;
		dx += GRAVITY_X;
		dy += GRAVITY_Y;
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
