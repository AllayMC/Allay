package org.allaymc.api.item.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.tag.ItemTag;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class ItemTagDescriptor implements ItemDescriptor {
    protected ItemTag itemTag;

    @Override
    public boolean match(ItemStack itemStack) {
        return itemStack.getItemType().hasItemTag(itemTag);
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptor toNetwork() {
        return new org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemTagDescriptor(
                itemTag.name()
        );
    }
}
