package org.allaymc.api.entity.component;

/**
 * {@code EntityAreaEffectCloudBaseComponent} exposes the radius, lifetime, and
 * per-target reuse delay of an area effect cloud. The server uses entity ticks
 * for timing and blocks for radius, applying potion effects periodically while
 * radius and duration determine when the cloud disappears.
 *
 * @author daoge_cmd
 */
public interface EntityAreaEffectCloudBaseComponent extends EntityBaseComponent {

    /**
     * Returns the configured cloud radius in blocks for this cloud.
     */
    float getRadius();

    /**
     * Sets the cloud radius in blocks used by this cloud.
     */
    void setRadius(float radius);

    /**
     * Returns the configured remaining active lifetime in ticks for this
     * cloud.
     */
    int getDuration();

    /**
     * Sets the remaining active lifetime in ticks used by this cloud.
     */
    void setDuration(int duration);

    /**
     * Returns the configured radius change in blocks per active tick for this
     * cloud.
     */
    float getRadiusPerTick();

    /**
     * Sets the radius change in blocks per active tick used by this cloud.
     */
    void setRadiusPerTick(float radiusPerTick);

    /**
     * Returns the configured radius change in blocks after affecting an
     * eligible entity for this cloud.
     */
    float getRadiusOnUse();

    /**
     * Sets the radius change in blocks after affecting an eligible entity used
     * by this cloud.
     */
    void setRadiusOnUse(float radiusOnUse);

    /**
     * Returns the configured per-entity effect reuse delay in ticks for this
     * cloud.
     */
    int getReapplicationDelay();

    /**
     * Sets the per-entity effect reuse delay in ticks used by this cloud.
     */
    void setReapplicationDelay(int reapplicationDelay);
}
