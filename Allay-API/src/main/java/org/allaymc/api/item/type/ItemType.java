package org.allaymc.api.item.type;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.initinfo.SimpleItemStackInitInfo;
import org.allaymc.api.item.tag.ItemTag;
import org.allaymc.api.utils.Identified;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public interface ItemType<T extends ItemStack> extends Identified {
    T createItemStack(ItemStackInitInfo info);

    default T createItemStack() {
        return createItemStack(1);
    }

    default T createItemStack(int count) {
        return createItemStack(count, 0);
    }

    default T createItemStack(int count, int meta) {
        return createItemStack(SimpleItemStackInitInfo.builder().count(count).meta(meta).build());
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
