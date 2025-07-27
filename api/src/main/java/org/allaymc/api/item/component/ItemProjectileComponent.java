package org.allaymc.api.item.component;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.event.entity.ProjectileLaunchEvent;

/**
 * ItemProjectileComponent is used in item that can be shot by entity. When right click,
 * the item will be consumed and an entity specified by {@link #getProjectileEntityType()}
 * will be created with the motion specified by {@link #getThrowForce()}.
 *
 * @author daoge_cmd
 */
public interface ItemProjectileComponent extends ItemComponent {
    /**
     * Get the projectile entity type of this item.
     *
     * @return the projectile entity type of this item.
     */
    EntityType<?> getProjectileEntityType();

    /**
     * Get the initial throw force when an entity shoot this item.
     *
     * @return the initial throw force when an entity shoot this item.
     */
    double getThrowForce();

    /**
     * Try to shoot this item by a specific entity.
     *
     * @param shooter the entity that shoots this item.
     * @return {@code true} if the projectile is shot successfully, {@code false} otherwise
     * (may because event {@link ProjectileLaunchEvent} being cancelled).
     */
    boolean shoot(Entity shooter);
}
