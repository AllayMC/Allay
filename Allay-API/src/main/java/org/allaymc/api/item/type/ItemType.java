package org.allaymc.api.item.type;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.tag.ItemTag;
import org.allaymc.api.utils.Identified;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.Set;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemType<T extends ItemStack> extends Identified {
    List<ComponentProvider<? extends ItemComponent>> getComponentProviders();

    T createItemStack(ItemStackInitInfo<T> info);

    default T createItemStack() {
        return createItemStack(SimpleItemStackInitInfo.builder().count(1).build());
    }

    int getRuntimeId();

    default Identifier getBlockIdentifier() {
        var blockType = getBlockType();
        return blockType == null ? null : blockType.getIdentifier();
    }

    BlockType<?> getBlockType();

    default ItemDefinition toNetworkDefinition() {
        return new SimpleItemDefinition(getIdentifier().toString(), getRuntimeId(), false);
    }


    @Unmodifiable
    Set<ItemTag> getItemTags();

    default boolean hasItemTag(ItemTag itemTag) {
        return getItemTags().contains(itemTag);
    }
}
