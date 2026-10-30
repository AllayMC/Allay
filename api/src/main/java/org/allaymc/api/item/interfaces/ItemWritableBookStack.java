package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemWritableBookBaseComponent;

/**
 * {@code ItemWritableBookStack} represents a writable book stack with
 * specialized behavior exposed through the item API. The inherited
 * {@link ItemStack} contract supplies ordinary stack operations, while
 * {@link ItemWritableBookBaseComponent} supplies page access, editing,
 * insertion, removal, and reordering operations.
 */
public interface ItemWritableBookStack extends ItemStack, ItemWritableBookBaseComponent {
}
