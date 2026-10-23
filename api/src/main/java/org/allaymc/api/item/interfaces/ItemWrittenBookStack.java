package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemWrittenBookBaseComponent;

/**
 * {@code ItemWrittenBookStack} represents a written book stack with
 * specialized behavior exposed through the item API. The inherited
 * {@link ItemStack} contract supplies ordinary stack operations, while
 * {@link ItemWrittenBookBaseComponent} supplies access to the signed book
 * metadata and stored page content.
 */
public interface ItemWrittenBookStack extends ItemStack, ItemWrittenBookBaseComponent {
}
