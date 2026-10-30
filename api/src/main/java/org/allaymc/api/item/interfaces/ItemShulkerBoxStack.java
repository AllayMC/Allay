package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemShulkerBoxBaseComponent;

/**
 * {@code ItemShulkerBoxStack} represents a shulker box stack with specialized
 * behavior exposed through the item API. The inherited {@link ItemStack}
 * contract supplies ordinary stack operations, while
 * {@link ItemShulkerBoxBaseComponent} exposes the inventory items stored by
 * their slot indices.
 */
public interface ItemShulkerBoxStack extends ItemStack, ItemShulkerBoxBaseComponent {
}
