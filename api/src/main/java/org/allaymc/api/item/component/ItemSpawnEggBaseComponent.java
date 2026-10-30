package org.allaymc.api.item.component;

import org.allaymc.api.entity.type.EntityType;

/**
 * {@code ItemSpawnEggBaseComponent} identifies the entity type produced by a
 * spawn egg item. The component exposes the spawn target independently of stack
 * count and metadata, allowing callers to inspect the entity type before
 * performing a placement interaction.
 *
 * @author daoge_cmd
 */
public interface ItemSpawnEggBaseComponent extends ItemBaseComponent {
    /**
     * Returns the entity type selected for this spawn egg.
     */
    EntityType<?> getEntityType();
}
