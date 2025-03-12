package org.allaymc.api.item.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.tag.ItemTag;

import java.util.Objects;

/**
 * Represents an item tag descriptor.
 * <p>
 * Item tag descriptors are used to match items based on their tags.
 *
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ItemTagDescriptor that)) {
            return false;
        }
        return Objects.equals(itemTag, that.itemTag);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(itemTag);
    }
}
