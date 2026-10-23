package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemBucketComponent;

/**
 * {@code ItemBucketStack} represents a bucket stack with specialized behavior
 * exposed through the item API. The inherited {@link ItemStack} contract
 * supplies ordinary stack operations, while {@link ItemBucketComponent}
 * exposes the contained liquid type and optional captured entity.
 */
public interface ItemBucketStack extends ItemStack, ItemBucketComponent {
}
