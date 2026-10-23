package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemWearableComponent;

/**
 * {@code ItemHeadStack} represents a head stack with specialized behavior
 * exposed through the item API. The inherited {@link ItemStack} contract
 * supplies ordinary stack operations, while {@link ItemWearableComponent}
 * exposes the armor slot used when the head is equipped.
 */
public interface ItemHeadStack extends ItemStack, ItemWearableComponent {
}
