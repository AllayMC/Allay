package org.allaymc.api.entity.component;

/**
 * Component interface for fireball (large fireball / ghast fireball) entities.
 * Unlike small fireballs, large fireballs create explosions on impact.
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
