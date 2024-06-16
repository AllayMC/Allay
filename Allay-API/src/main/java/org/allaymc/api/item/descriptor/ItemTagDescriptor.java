package org.allaymc.api.item.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class ItemTagDescriptor implements ItemDescriptor {
    protected String itemTag;

    @Override
    public boolean match(ItemStack itemStack) {
        return itemStack.getItemType().hasItemTag(itemTag);
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptor toNetwork() {
        return new org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemTagDescriptor(
                itemTag
        );
    }
}
