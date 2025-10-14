package org.allaymc.api.entity.component;

/**
 * @author daoge_cmd
 */
public interface EntityFireworksRocketPhysicsComponent extends EntityPhysicsComponent {

    /**
     * The default multiplier for sideways motion applied to the entity's horizontal movement along the x and z axes
     * in each tick.
     */
    double DEFAULT_SIDEWAYS_MOTION_MULTIPLIER = 1.15;
    /**
     * The default value for upwards motion that is applied to the vertical (y-axis) motion of an entity every tick.
     */
    double DEFAULT_UPWARDS_MOTION = 0.04;

    /**
     * Gets the sideways motion multiplier, which will be multiplied to motion in x/z every tick.
     *
     * @return the sideways motion multiplier
     */
    double getSidewaysMotionMultiplier();

    /**
     * Sets the sideways motion multiplier, which will be applied to the x and z motion of the entity every tick.
     *
     * @param multiplier the sideways motion multiplier to set; must be a valid double value
     */
    void setSidewaysMotionMultiplier(double multiplier);

    /**
     * Gets the upwards motion, which will be added to the y motion of the entity every tick.
     *
     * @return the upwards motion multiplier
     */
    double getUpwardsMotion();

    /**
     * Sets the upwards motion, which will be added to the vertical (y-axis) motion of the entity every tick.
     *
     * @param motion the upwards motion to set; must be a valid double value
     */
    void setUpwardsMotion(double motion);
}
