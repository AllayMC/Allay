package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemBannerBaseComponent;

/**
 * {@code ItemBannerStack} represents a banner stack with specialized behavior
 * exposed through the item API. The inherited {@link ItemStack} contract
 * supplies ordinary stack operations, while {@link ItemBannerBaseComponent}
 * exposes base color, banner kind, and ordered pattern editing.
 */
public interface ItemBannerStack extends ItemStack, ItemBannerBaseComponent {
}
