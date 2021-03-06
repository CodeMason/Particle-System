package particle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

/**
 * Represents a particle in 2D space.
 * @author Valkryst
 * --- Last Edit 29-January-2015
 */
public class Particle {
    /** The currentlocation of the particle on the X-axis. */
    private float xCurrent;
    /** The current location of the particle on the Y-axis. */
    private float yCurrent;
    /** The change in X, per update, of the particle. */
    private float dx;
    /** The change in Y, per update, of the particle. */
    private float dy;
    /** The gravitational pull to the left (negative) and right (positive) acting on this particle. */
    private float gravityX;
    /** The gravitational pull to the up (negative) and down (positive) acting on this particle. */
    private float gravityY;
    /** The size in pixels^2 of the particle. */
    private byte size;
    /** The remaining lifetime of the particle. */
    private float currentLife;
    /** The total lifetime of the particle. */
    private short totalLife;
    /** The color of the particle. */
    private Color color;

    /** Constructs a new particle. */
    public Particle() {}

    /**
     * Sets all of the data of the particle.
     * @param xCurrent The current location of the effect on the X-axis.
     * @param yCurrent The currentlocation of the partivcle on the Y-axis.
     * @param dx The change in X, per update, of the effect.
     * @param dy The change in Y, per update, of the effect.
     * @param size The size in pixels^2 of the effect.
     * @param life The remaining lifetime of the effect.
     * @param color The color of the effect.
     */
    public void setAllData(final float xCurrent, final float yCurrent, final float dx, final float dy, final byte size, final short life, final Color color) {
        this.xCurrent = xCurrent;
        this.yCurrent = yCurrent;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        currentLife = life;
        totalLife = life;
        this.color = color;
    }

    /**
     * Sets all of the data of the particle.
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
    public void setAllData(final float xCurrent, final float yCurrent, final float dx, final float dy, final float gravityX, final float gravityY, final byte size, final short life, final Color color) {
        this.xCurrent = xCurrent;
        this.yCurrent = yCurrent;
        this.dx = dx;
        this.dy = dy;
        this.gravityX = gravityX;
        this.gravityY = gravityY;
        this.size = size;
        currentLife = life;
        totalLife = life;
        this.color = color;
    }

    /**
     * Updates, nearly, every variable of the effect to, hopefully,
     * match-up with the new screen dimensions that the effect is to
     * be drawn onto.
     * @param currentScreenDimensions The current screen dimensions for the screen on which the particle is to be drawn.
     * @param newScreenDimensions The new screen dimensions for the screen on which the particle is to be drawn.
     */
    public void updateForNewScreenDimensions(final Dimension currentScreenDimensions, final Dimension newScreenDimensions) {
        short widthCurrent = (short)currentScreenDimensions.width;
        short heightCurrent = (short)currentScreenDimensions.height;
        short widthNew = (short)newScreenDimensions.width;
        short heightNew = (short)newScreenDimensions.height;

        // Calculate the % difference between the widths and heights.
        short widthDifference = (short)(widthNew / widthCurrent);
        short heightDifference = (short)(heightNew / heightCurrent);

        // Change the effect's coordinates, velocity, gravitational pull, size, and life.
        xCurrent *= widthDifference;
        dx  *= widthDifference;
        gravityX *= widthDifference;

        yCurrent *= heightDifference;
        dy *= heightDifference;
        gravityY *= heightDifference;

        // Update the size and life using the average of the two for a simple result.
        float averagePercentDifference = (widthDifference + heightDifference) / 2;
        currentLife *= averagePercentDifference;
        totalLife *= averagePercentDifference;
    }

    /**
     * If the effect is off-screen then return true. If true is returned then
     * the calling effect's update() method delete the effect.
     *
     * Else updates the effect's position, change in xCurrent, change in yCurrent,
     * remaining lifetime, and color.
     *
     * @param screenDimensions The screen dimensions for the screen on which the particle is to be drawn.
     */
    public void update(final Dimension screenDimensions) {
        if(xCurrent > screenDimensions.width + 32 || xCurrent < -32 || yCurrent <= -32) {
            currentLife = 0;
        } else {
            xCurrent += dx;
            yCurrent += dy;

            dx += gravityX;
            dy += gravityY;

            currentLife--;

            float alpha = (currentLife / totalLife);
            color.set(color.r, color.g, color.b, (alpha >= 0.0f ? alpha : 0.0f));
        }
    }

    /**
     * Renders the effect to the screen.
     * @param isOval Whether or not to render the effect as an oval.
     */
    public void render(final ShapeRenderer renderer, final boolean isOval) {
        renderer.setColor(color);

        if(isOval) {
            renderer.circle((int) xCurrent - (size / 2), (int) yCurrent - (size / 2), size, size); //xCurrent-(size/2) & yCurrent-(size/2) make sure the effect is rendered at (xCurrent, yCurrent).
        } else {
            renderer.rect((int) xCurrent - (size / 2), (int) yCurrent - (size / 2), size, size); //xCurrent-(size/2) & yCurrent-(size/2) make sure the effect is rendered at (xCurrent, yCurrent).
        }
    }

    /** @return Whether-or-not the particle is alive. */
    public boolean isAlive() {
        return currentLife > 0;
    }

    public float getXCurrent() {
        return xCurrent;
    }

    public float getYCurrent() {
        return yCurrent;
    }

    public float getGravityX() {
        return gravityX;
    }

    public float getGravityY() {
        return gravityY;
    }

    public void setGravityX(final float gravityX) {
        this.gravityX = gravityX;
    }

    public void setGravityY(final float gravityY) {
        this.gravityY = gravityY;
    }

    public void setCurrentLife(final float currentLife) {
        this.currentLife = currentLife;
    }
}