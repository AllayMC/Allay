package org.allaymc.api.entity.component;

/**
 * Shared component for explosive fireball entities with configurable explosion power.
 *
 * @author daoge_cmd
 */
public interface EntityFireballBaseComponent extends EntityBaseComponent {

    /**
     * Gets the explosion power of this fireball.
     *
     * @return the explosion power
     */
    float getExplosionPower();

    /**
     * Sets the explosion power of this fireball.
     *
     * @param power the explosion power
     */
    void setExplosionPower(float power);
}
