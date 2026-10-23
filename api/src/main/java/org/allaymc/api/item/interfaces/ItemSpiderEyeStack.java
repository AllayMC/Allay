package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemEdibleComponent;

/**
 * {@code ItemSpiderEyeStack} represents a spider eye stack with specialized
 * behavior exposed through the item API. The inherited {@link ItemStack}
 * contract supplies ordinary stack operations, while
 * {@link ItemEdibleComponent} exposes eating eligibility, eating time,
 * nutrition values, drink behavior, and consumption callbacks.
 */
public interface ItemSpiderEyeStack extends ItemStack, ItemEdibleComponent {
}
