package org.allaymc.api.item.descriptor;

import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface ItemDescriptor {
    boolean match(ItemStack itemStack);

    org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptor toNetwork();
}
