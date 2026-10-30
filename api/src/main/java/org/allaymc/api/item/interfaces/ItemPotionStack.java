package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemPotionComponent;

/**
 * {@code ItemPotionStack} represents a potion stack with specialized behavior
 * exposed through the item API. The inherited {@link ItemStack} contract
 * supplies ordinary stack operations, while {@link ItemPotionComponent}
 * exposes the selected potion type that determines the contained effects.
 */
public interface ItemPotionStack extends ItemStack, ItemPotionComponent {
}
