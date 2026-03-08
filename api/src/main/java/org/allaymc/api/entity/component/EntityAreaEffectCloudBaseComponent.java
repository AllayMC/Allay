package org.allaymc.api.entity.component;

/**
 * @author daoge_cmd
 */
public interface EntityAreaEffectCloudBaseComponent extends EntityBaseComponent {

    /**
     * Gets the current radius of the cloud.
     *
     * @return the radius in blocks
     */
    float getRadius();

    /**
     * Sets the radius of the cloud.
     *
     * @param radius the radius in blocks
     */
    void setRadius(float radius);

    /**
     * Gets the remaining duration of the cloud in ticks.
     *
     * @return the duration in ticks
     */
    int getDuration();

    /**
     * Sets the duration of the cloud in ticks.
     *
     * @param duration the duration in ticks
     */
    void setDuration(int duration);

    /**
     * Gets the radius change per tick.
     *
     * @return the radius change per tick (usually negative)
     */
    float getRadiusPerTick();

    /**
     * Sets the radius change per tick.
     *
     * @param radiusPerTick the radius change per tick
     */
    void setRadiusPerTick(float radiusPerTick);

    /**
     * Gets the radius change when the cloud is used (applies effect to an entity).
     *
     * @return the radius change on use (usually negative)
     */
    float getRadiusOnUse();

    /**
     * Sets the radius change when the cloud is used.
     *
     * @param radiusOnUse the radius change on use
     */
    void setRadiusOnUse(float radiusOnUse);

    /**
     * Gets the delay in ticks before effects can be reapplied to an entity.
     *
     * @return the reapplication delay in ticks
     */
    int getReapplicationDelay();

    /**
     * Sets the reapplication delay.
     *
     * @param reapplicationDelay the delay in ticks
     */
    void setReapplicationDelay(int reapplicationDelay);
}
