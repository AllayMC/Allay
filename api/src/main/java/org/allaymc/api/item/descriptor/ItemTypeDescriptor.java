package org.allaymc.api.item.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;

import java.util.Objects;

/**
 * ItemTypeDescriptor matches items based on their {@link ItemType} and meta.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class ItemTypeDescriptor implements ItemDescriptor {

    public static final int WILDCARD_META = 32767;

    protected ItemType<?> itemType;
    protected int meta;

    public ItemTypeDescriptor(ItemType<?> itemType) {
        this(itemType, WILDCARD_META);
    }

    @Override
    public boolean match(ItemStack itemStack) {
        return itemStack.getItemType().getIdentifier().equals(itemType.getIdentifier()) &&
               (meta == WILDCARD_META || itemStack.getMeta() == meta);
    }

    public ItemStack createItemStack() {
        return itemType.createItemStack(1, meta);
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptor toNetwork() {
        return new org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.DefaultDescriptor(
                itemType.toNetworkDefinition(),
                meta
        );
    }

    @Override
    public String toString() {
        return itemType.getIdentifier() + ":" + meta;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ItemTypeDescriptor that)) {
            return false;
        }
        return meta == that.meta && Objects.equals(itemType, that.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemType, meta);
    }
}
