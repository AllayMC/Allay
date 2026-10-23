package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemSpawnEggBaseComponent;

/**
 * {@code ItemSpawnEggStack} represents a spawn egg stack that exposes its spawned entity
 * type. The inherited item contract supplies stack state and interactions,
 * while {@code ItemSpawnEggBaseComponent} lets callers inspect the entity type
 * selected by the egg before a placement interaction.
 */
public interface ItemSpawnEggStack extends ItemStack, ItemSpawnEggBaseComponent {
}
