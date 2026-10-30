package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemMusicDiscBaseComponent;

/**
 * {@code ItemMusicDiscStack} represents a music disc stack with specialized
 * behavior exposed through the item API. The inherited {@link ItemStack}
 * contract supplies ordinary stack operations, while
 * {@link ItemMusicDiscBaseComponent} exposes the disc type and its comparator
 * output in a jukebox.
 */
public interface ItemMusicDiscStack extends ItemStack, ItemMusicDiscBaseComponent {
}
