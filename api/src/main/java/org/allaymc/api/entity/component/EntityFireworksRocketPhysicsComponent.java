package org.allaymc.api.entity.component;

/**
 * {@code EntityFireworksRocketPhysicsComponent} configures the acceleration of
 * a freely flying firework rocket. The horizontal multiplier scales existing
 * motion each tick, while upward motion adds vertical acceleration; attachment
 * suppresses this free-flight update while the rocket follows its player.
 *
 * @author daoge_cmd
 */
public interface EntityFireworksRocketPhysicsComponent extends EntityPhysicsComponent {

    /**
     * {@code DEFAULT_SIDEWAYS_MOTION_MULTIPLIER} initializes the per-tick
     * horizontal scaling used by freely flying rockets.
     */
    double DEFAULT_SIDEWAYS_MOTION_MULTIPLIER = 1.15;
    /**
     * {@code DEFAULT_UPWARDS_MOTION} initializes free-flight upward
     * acceleration in blocks per tick squared for rockets.
     */
    double DEFAULT_UPWARDS_MOTION = 0.04;

    /**
     * Returns the per-tick multiplier applied to horizontal free-flight
     * motion.
     */
    double getSidewaysMotionMultiplier();

    /**
     * Stores the per-tick multiplier applied to horizontal free-flight motion.
     */
    void setSidewaysMotionMultiplier(double multiplier);

    /**
     * Returns free-flight upward acceleration in blocks per tick squared.
     */
    double getUpwardsMotion();

    /**
     * Stores free-flight upward acceleration in blocks per tick squared.
     */
    void setUpwardsMotion(double motion);
}
