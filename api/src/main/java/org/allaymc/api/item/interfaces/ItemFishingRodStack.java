package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemRepairableComponent;

/**
 * {@code ItemFishingRodStack} represents a fishing rod stack with specialized
 * behavior exposed through the item API. The inherited {@link ItemStack}
 * contract supplies ordinary stack operations, while
 * {@link ItemRepairableComponent} identifies the item types that can serve as
 * repair ingredients.
 */
public interface ItemFishingRodStack extends ItemStack, ItemRepairableComponent {
}
