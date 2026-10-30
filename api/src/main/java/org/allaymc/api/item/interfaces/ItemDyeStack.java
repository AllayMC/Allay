package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemDyeComponent;

/**
 * {@code ItemDyeStack} represents a dye stack with specialized behavior
 * exposed through the item API. The inherited {@link ItemStack} contract
 * supplies ordinary stack operations, while {@link ItemDyeComponent} exposes
 * the fixed dye color associated with this item.
 */
public interface ItemDyeStack extends ItemStack, ItemDyeComponent {
}
