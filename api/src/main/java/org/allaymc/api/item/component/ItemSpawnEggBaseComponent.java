package org.allaymc.api.item.component;

import org.allaymc.api.entity.type.EntityType;

/**
 * Component for spawn egg items that can spawn entities.
 *
 * @author daoge_cmd
 */
public interface ItemSpawnEggBaseComponent extends ItemBaseComponent {
    /**
     * Gets the entity type that this spawn egg will spawn.
     *
     * @return the entity type to spawn
     */
    EntityType<?> getEntityType();
}
