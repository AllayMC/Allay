package org.allaymc.api.item.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;

/**
 * Represents an item descriptor with count.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class ItemDescriptorWithCount {
    protected ItemDescriptor itemDescriptor;
    protected int count;

    public boolean match(ItemStack itemStack) {
        return itemDescriptor.match(itemStack) && itemStack.getCount() == count;
    }

    public org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount toNetwork() {
        return new org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount(
                itemDescriptor.toNetwork(),
                count
        );
    }
}
