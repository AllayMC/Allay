package org.allaymc.api.entity.component;

import org.allaymc.api.entity.Entity;

/**
 * {@code EntityProjectileComponent} retains the entity associated with a
 * projectile launch. Collision filtering and damage attribution consult this
 * shooter reference, which can remain {@code null} for projectiles without an
 * assigned shooter and does not own the shooter lifecycle.
 *
 * @author daoge_cmd
 */
public interface EntityProjectileComponent extends EntityComponent {
    /**
     * Returns the retained shooter reference, or {@code null} when none is
     * assigned. The projectile keeps this reference directly, and consumers
     * must separately check shooter lifetime or dimension when relevant.
     */
    Entity getShooter();

    /**
     * Retains the supplied shooter reference for later projectile consumers
     * without copying or lifecycle management. Passing {@code null} clears
     * attribution; this setter does not launch or reposition the projectile.
     */
    void setShooter(Entity shooter);
}
