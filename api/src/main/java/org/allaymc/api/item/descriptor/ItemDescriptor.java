package org.allaymc.api.item.descriptor;

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
     * Check if the given {@link ItemStack} matches this descriptor.
     *
     * @param itemStack The {@link ItemStack} to check.
     *
     * @return {@code true} if the {@link ItemStack} matches this descriptor, {@code false} otherwise.
     */
    boolean match(ItemStack itemStack);

    /**
     * Convert this descriptor to a network descriptor.
     *
     * @return The network descriptor.
     */
    org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptor toNetwork();

    default org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount toNetworkWithCount() {
        return new org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount(toNetwork(), 1);
    }
}
