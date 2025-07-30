package org.allaymc.api.entity.component;

import org.allaymc.api.entity.Entity;

/**
 * @author daoge_cmd
 */
public interface EntityProjectileBaseComponent extends EntityBaseComponent {
    /**
     * Gets the entity that shots the projectile.
     *
     * @return the entity that shots the projectile.
     */
    Entity getShooter();

    /**
     * Sets the entity that shots the projectile.
     *
     * @param shooter the entity that shots the projectile.
     */
    void setShooter(Entity shooter);
}
