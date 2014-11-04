package particle;

import java.awt.*;

/**
 * Represents a particle in 2D space.
 * @author Valkryst
 * --- Last Edit 04-November-2014
 */
public class ParticleHole {
    /** The location of the particle hole on the x-axis. */
    private float xPosition;
    /** The location of the particle hole on the y-axis. */
    private float yPosition;
    /** The gravitational pull, on the x-axis, of the particle hole. */
    private float xGravitationalPull;
    /** The gravitational pull, on the y-axis, of the particle hole. */
    private float yGravitationalPull;


    /**
     * Construct a particle hole with separate gravitational pulls on both the x and y axis.
     * @param xPosition The location of the particle hole on the x-axis.
     * @param yPosition The location of the particle hole on the y-axis.
     * @param xGravitationalPull The gravitational pull, on the x-axis, of the particle hole.
     * @param yGravitationalPull The gravitational pull, on the y-axis, of the particle hole.
     */
    public ParticleHole(final float xPosition, final float yPosition, final float xGravitationalPull, final float yGravitationalPull) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xGravitationalPull = xGravitationalPull;
        this.yGravitationalPull = yGravitationalPull;
    }

    /**
     * Construct a particle hole with the same gravitational pull on both the x and y axis.
     * @param xPosition The location of the particle hole on the x-axis.
     * @param yPosition The location of the particle hole on the y-axis.
     * @param gravitationalPull The gravitational pull, on the x and y axis, of the particle hole.
     */
    public ParticleHole(final float xPosition, final float yPosition, final float gravitationalPull) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        xGravitationalPull = gravitationalPull;
        yGravitationalPull = gravitationalPull;
    }

    /**
     * Applies the gravitational force of the particle hole to
     * the specified particle.
     * @param p The particle to apply the gravitational force to.
     * @param screenDimensions The screen dimensions.
     */
    public void applyGravitationalForceToParticle(final Particle p, final Dimension screenDimensions) {
        float differenceX = xPosition - p.getXCurrent();
        float differenceY = yPosition - p.getYCurrent();

        differenceX = differenceX / screenDimensions.width;
        differenceY = differenceY / screenDimensions.height;

        p.setGravityX(p.getGravityX() + (xGravitationalPull * differenceX));
        p.setGravityY(p.getGravityY() + (yGravitationalPull * differenceY));
    }

    public float getXPosition() {
        return xPosition;
    }

    public float getYPosition() {
        return yPosition;
    }

    public void setXPosition(final float xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(final float yPosition) {
        this.yPosition = yPosition;
    }
}
