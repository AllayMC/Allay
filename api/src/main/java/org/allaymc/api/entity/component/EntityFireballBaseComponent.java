package org.allaymc.api.entity.component;

/**
 * {@code EntityFireballBaseComponent} exposes the explosion strength carried
 * by an explosive fireball. Impact handling reads this value when constructing
 * an explosion, so callers can configure the explosion independently of the
 * projectile movement and shooter reference.
 *
 * @author daoge_cmd
 */
public interface EntityFireballBaseComponent extends EntityBaseComponent {

    /**
     * Returns the fireball strength supplied to its impact explosion.
     */
    float getExplosionPower();

    /**
     * Stores the fireball strength used when impact creates an explosion.
     */
    void setExplosionPower(float power);
}
