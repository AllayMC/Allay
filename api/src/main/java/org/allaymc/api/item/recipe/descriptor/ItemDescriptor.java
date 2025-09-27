package org.allaymc.api.item.recipe.descriptor;

import org.allaymc.api.item.ItemStack;

/**
 * Represents an item descriptor.
 * <p>
 * Item descriptors are used to match items, and it is widely used in recipes.
 *
 * @author daoge_cmd
 */
public interface ItemDescriptor {
    /**
     * Checks if an item stack matches this descriptor.
     *
     * @param itemStack the {@link ItemStack} to check
     * @return {@code true} if matched, {@code false} otherwise
     */
    boolean match(ItemStack itemStack);
}
