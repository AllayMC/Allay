package org.allaymc.api.item.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;

/**
 * Pairs an {@link ItemDescriptor} with a concrete item count.
 * <p>
 * This descriptor is considered a match for an {@link org.allaymc.api.item.ItemStack}
 * only if both the underlying {@link ItemDescriptor} matches and the stack's count
 * equals the specified {@code count}.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class ItemDescriptorWithCount {
    protected ItemDescriptor itemDescriptor;
    protected int count;

    /**
     * Checks whether the given {@link ItemStack} matches this descriptor and count.
     *
     * @param itemStack the item stack to test
     * @return {@code true} if {@code itemDescriptor} matches and {@code itemStack.getCount() == count}
     */
    public boolean match(ItemStack itemStack) {
        return itemDescriptor.match(itemStack) && itemStack.getCount() == count;
    }

    // TODO: remove
    public org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount toNetwork() {
        return new org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount(
                itemDescriptor.toNetwork(),
                count
        );
    }
}
