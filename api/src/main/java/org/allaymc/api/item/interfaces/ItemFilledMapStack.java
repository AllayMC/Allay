package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemFilledMapBaseComponent;

/**
 * {@code ItemFilledMapStack} represents a filled map stack with specialized
 * behavior exposed through the item API. The inherited {@link ItemStack}
 * contract supplies ordinary stack operations, while
 * {@link ItemFilledMapBaseComponent} supplies image access, asynchronous map
 * rendering, and delivery to players.
 */
public interface ItemFilledMapStack extends ItemStack, ItemFilledMapBaseComponent {
}
