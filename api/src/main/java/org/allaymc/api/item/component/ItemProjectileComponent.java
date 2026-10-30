package org.allaymc.api.item.component;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.event.entity.ProjectileLaunchEvent;

/**
 * {@code ItemProjectileComponent} describes an item's projectile type and launch
 * force and exposes an attempt to launch it. The launch result lets interaction
 * handlers distinguish a successful shot from a rejected projectile or a cancelled
 * launch event.
 *
 * @author daoge_cmd
 */
public interface ItemProjectileComponent extends ItemComponent {
    /**
     * Returns the entity type configured for projectiles from this item.
     */
    EntityType<?> getProjectileEntityType();

    /**
     * Returns the initial motion multiplier configured for this projectile item.
     */
    double getThrowForce();

    /**
     * Attempts to launch this item's projectile from the supplied entity.
     * A successful launch returns {@code true}; {@code false} includes rejection of
     * the projectile or cancellation of {@link ProjectileLaunchEvent} before launch.
     */
    boolean shoot(Entity shooter);
}
