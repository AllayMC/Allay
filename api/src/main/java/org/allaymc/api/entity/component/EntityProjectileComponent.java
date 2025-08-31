package org.allaymc.api.entity.component;

import org.allaymc.api.entity.Entity;

/**
 * @author daoge_cmd
 */
public interface EntityProjectileComponent extends EntityComponent {
    /**
     * Gets the entity that shots the projectile.
     *
     * @return the entity that shots the projectile
     */
    Entity getShooter();

    /**
     * Sets the entity that shots the projectile.
     *
     * @param shooter the entity that shots the projectile
     */
    void setShooter(Entity shooter);
}
