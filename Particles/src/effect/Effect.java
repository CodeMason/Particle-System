package effect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import particle.Particle;

import java.awt.*;

/**
 * Represents a particle effect.
 * @author Valkryst
 * --- Last Edit 29-January-2015
 */
public class Effect {
    /** The dimensions of the canvas on which the effect is to be rendered. */
    protected Dimension screenDimensions;
	/** A collection of particles that make up the snow.*/
	protected final Particle[] particles;
	/** The origin of this snow on the X-axis.*/
	protected float originX;
	/** The origin of this snow on the Y-axis.*/
	protected float originY;
	/** Whether or not to render the particles as ovals. If not then render as squares. Ovals are extremely CPU intensive for large effects*/
	protected final boolean isOval;
    protected Texture particleTexture;
    /** Counts the number of update calls since the last creation of new particles. */
	protected byte counter = 0;

    /**
     * Basic constructor for an effect.
     * @param screenDimensions The dimensions for the screen on which all particles are to be drawn.
     * @param originX The origin, on the X-axis, of the effect.
     * @param originY The origin, on the Y-axis, of the effect.
     * @param isOval Whether or not to render the particles as ovals. If not then they are rendered as squares.
     * @param totalParticles The total number of particles that this effect will be allowed to use.
     */
	public Effect(final Dimension screenDimensions, final float originX, final float originY, final boolean isOval, final short totalParticles) {
        this.screenDimensions = screenDimensions;
		this.originX = originX;
		this.originY = originY;
		this.isOval = isOval;
        particles = new Particle[totalParticles];

        for(short i=0;i<particles.length;i++) {
            particles[i] = new Particle();
        }
	}
	
	/**
	 * Updates half of all of the particles of the effect.
     * On the first call, the particles from 0 to (particles.length/2) are updated.
     * On the second call, the particles from ((particles.length/2)+1) to particles.length are updated.
     *
     * This algorithm of only updating half of the particles at any given time should, in theory, reduce the
     * resources used on every update call. The alternative is to update every single particle on every single update
     * call which happens multiple times a second.
	 */
	public void update() {
        for(Particle particle : particles) {
            if(particle.isAlive()) {
                particle.update(screenDimensions);
            }
        }
    }
	
	/**
	 * Renders the snow to the screen.
	 * @param renderer Graphics object with which to draw.
	 */
	public void render(final ShapeRenderer renderer) {
        for(Particle p : particles) {
            if(p.isAlive()) {
                p.render(renderer, isOval);
            }
        }
	}

    /** @return Whether-or-not the effect can hold any more particles. */
    public short canTakeNewParticles() {
        for(short i=0;i<particles.length;i++) {
            if(!particles[i].isAlive()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sets all of the data of the specified particle to the new data provided.
     * @param index The index, of the particle array, where the particle is to be added.
     * @param xCurrent The current location of the effect on the X-axis.
     * @param yCurrent The currentlocation of the particle on the Y-axis.
     * @param dx The change in X, per update, of the effect.
     * @param dy The change in Y, per update, of the effect.
     * @param size The size in pixels^2 of the effect.
     * @param life The remaining lifetime of the effect.
     * @param color The color of the effect.
     */
    public void addParticle(final short index, final float xCurrent, final float yCurrent, final float dx, final float dy, final byte size, final short life, final Color color) {
        particles[index].setAllData(xCurrent, yCurrent, dx, dy, size, life, color);
    }

    /**
     * Sets all of the data of the specified particle to the new data provided.
     * @param index The index, of the particle array, where the particle is to be added.
     * @param xCurrent The current location of the effect on the X-axis.
     * @param yCurrent The currentlocation of the particle on the Y-axis.
     * @param dx The change in X, per update, of the effect.
     * @param dy The change in Y, per update, of the effect.
     * @param gravityX The gravitational pull to the left (negative) and right (positive) acting on this effect.
     * @param gravityY The gravitational pull to the up (negative) and down (positive) acting on this effect.
     * @param size The size in pixels^2 of the effect.
     * @param life The remaining lifetime of the effect.
     * @param color The color of the effect.
     */
    public void addParticle(final short index, final float xCurrent, final float yCurrent, final float dx, final float dy, final float gravityX, final float gravityY, final byte size, final short life, final Color color) {
        particles[index].setAllData(xCurrent, yCurrent, dx, dy, gravityX, gravityY, size, life, color);
    }

    /** Deletes all particles held by the effect. */
    public void clearParticles() {
        for(short i=0;i<particles.length;i++) {
            particles[i].setCurrentLife(0);
        }
    }

    /**
     * Sets the screen dimensions, for the Effect, to the specified dimensions, and then
     * updates all of the particles in the Effect to work with the new dimensions.
     *
     * @param newScreenDimensions The new screen dimensions for the screen on which the particle is to be drawn.
     * @param originX The new x-axis origin of the effect.
     * @param originY The new y-axis origin of the effect.
     */
    public void setScreenDimensions(final Dimension newScreenDimensions, final float originX, final float originY) {
        // Update all particles to work properly with the new screen dimensions.
        for(Particle p : particles) {
            p.updateForNewScreenDimensions(screenDimensions, newScreenDimensions);
        }

        screenDimensions = newScreenDimensions;
        this.originX = originX;
        this.originY = originY;
    }
}
