package org.allaymc.api.item.type;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.tag.ItemTag;
import org.allaymc.api.utils.Identified;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Set;

/**
 * Represents a type of item.
 *
 * @author daoge_cmd
 */
public interface ItemType<T extends ItemStack> extends Identified {
    /**
     * Create an {@link ItemStack} with the given {@link ItemStackInitInfo}.
     *
     * @param info The {@link ItemStackInitInfo} to create the {@link ItemStack} with.
     *
     * @return The created {@link ItemStack}.
     */
    T createItemStack(ItemStackInitInfo info);

    /**
     * Create an {@link ItemStack} with a count of 1 and a meta of 0.
     *
     * @return The created {@link ItemStack}.
     */
    default T createItemStack() {
        return createItemStack(1);
    }

    /**
     * Create an {@link ItemStack} with the given count and a meta of 0.
     *
     * @param count The count of the {@link ItemStack}.
     *
     * @return The created {@link ItemStack}.
     */
    default T createItemStack(int count) {
        return createItemStack(count, 0);
    }

    /**
     * Create an {@link ItemStack} with the given count and meta.
     *
     * @param count The count of the {@link ItemStack}.
     * @param meta  The meta of the {@link ItemStack}.
     *
     * @return The created {@link ItemStack}.
     */
    default T createItemStack(int count, int meta) {
        return createItemStack(ItemStackInitInfo.builder().count(count).meta(meta).build());
    }

    /**
     * Get the runtime ID of the item.
     * <p>
     * The runtime id of an item is mutable and will change
     * between different versions of the game.
     *
     * @return The runtime ID of the item.
     */
    int getRuntimeId();

    /**
     * Get the block type of the item.
     *
     * @return The block type of the item.
     */
    BlockType<?> getBlockType();

    /**
     * Create the network definition of the item.
     *
     * @return The network definition of the item.
     */
    default ItemDefinition toNetworkDefinition() {
        return new SimpleItemDefinition(getIdentifier().toString(), getRuntimeId(), false);
    }

    /**
     * Get the item tags of the item.
     *
     * @return The item tags of the item.
     */
    @Unmodifiable
    Set<ItemTag> getItemTags();

    /**
     * Check if the item has the given item tag.
     *
     * @param itemTag The item tag to check.
     *
     * @return {@code true} if the item has the item tag, {@code false} otherwise.
     */
    default boolean hasItemTag(ItemTag itemTag) {
        return getItemTags().contains(itemTag);
    }
}
