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
     * Checks if an item stack matches this descriptor.
     *
     * @param itemStack the {@link ItemStack} to check
     *
     * @return {@code true} if matched, {@code false} otherwise
     */
    boolean match(ItemStack itemStack);

    /**
     * Converts to a network descriptor.
     *
     * @return the {@link org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptor}
     */
    org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptor toNetwork();

    /**
     * Converts to a network descriptor with count.
     *
     * @return the {@link org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount} with count 1
     */
    default org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount toNetworkWithCount() {
        return new org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount(toNetwork(), 1);
    }
}
