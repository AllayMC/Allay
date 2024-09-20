package org.allaymc.api.item.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class DefaultDescriptor implements ItemDescriptor {

    public static final int WILDCARD_META = 32767;

    protected ItemType<?> itemType;
    protected int meta;

    public DefaultDescriptor(ItemType<?> itemType) {
        this(itemType, WILDCARD_META);
    }

    @Override
    public boolean match(ItemStack itemStack) {
        return itemStack.getItemType().getIdentifier().equals(itemType.getIdentifier()) &&
               (meta == WILDCARD_META || itemStack.getMeta() == meta);
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptor toNetwork() {
        return new org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.DefaultDescriptor(
                itemType.toNetworkDefinition(),
                meta
        );
    }
}
