package org.allaymc.api.item.type;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.data.ItemData;
import org.allaymc.api.item.data.ItemTag;
import org.allaymc.api.utils.identifier.Identified;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Set;

/**
 * {@code ItemType} identifies an item kind and exposes its shared gameplay data,
 * tags, optional block association, and stack factory. Individual stacks hold
 * their own count and extra state; the type supplies the component-backed
 * capabilities used when those stacks are created.
 *
 * @param <T> stack interface produced by this item type
 *
 * @author daoge_cmd
 */
public interface ItemType<T extends ItemStack> extends Identified {
    /**
     * Creates a stack using the supplied component initialization values.
     * The item factory assigns this type to {@code info} before component creation;
     * ordinary stacks require initialization values, while the air implementation
     * can ignore them.
     */
    T createItemStack(ItemStackInitInfo info);

    /**
     * Requests a stack initialized with count {@code 1} and metadata {@code 0}.
     * The initialization builder requests a new network ID and empty extra NBT.
     * Ordinary stacks use those values; air uses predefined zero count and metadata
     * without allocating a network ID.
     */
    default T createItemStack() {
        return createItemStack(1);
    }

    /**
     * Requests a stack initialized with the supplied count and metadata {@code 0}.
     * The builder requests a new network ID and empty extra NBT. Ordinary stacks
     * use those values; air ignores them and uses predefined zero count and metadata
     * without allocating an ID.
     */
    default T createItemStack(int count) {
        return createItemStack(count, 0);
    }

    /**
     * Requests a stack initialized with the supplied count and metadata values.
     * The builder requests a new network ID and empty extra NBT. The selected
     * components interpret that initialization; air ignores it and uses predefined
     * zero count and metadata without allocating an ID.
     */
    default T createItemStack(int count, int meta) {
        return createItemStack(ItemStackInitInfo.builder().count(count).meta(meta).build());
    }

    /**
     * Returns the runtime identifier assigned to this registered item type.
     * Runtime identifiers depend on the loaded item definitions and game version;
     * callers should use the namespaced identifier for persistent identity.
     */
    int getRuntimeId();

    /**
     * Returns the block type associated with this item, if one exists.
     * The result is {@code null} for an item without a mapped block, so callers
     * must check it before requesting block state or placement behavior.
     */
    BlockType<?> getBlockType();

    /**
     * Returns the classification-tag set associated with this item type.
     * Callers must treat the set as read-only under this API contract; the server
     * type retains and exposes its selected set without universally wrapping it.
     */
    @Unmodifiable
    Set<ItemTag> getItemTags();

    /**
     * Returns whether this item type contains the supplied classification tag.
     */
    default boolean hasItemTag(ItemTag itemTag) {
        return getItemTags().contains(itemTag);
    }

    /**
     * Returns the gameplay properties shared by stacks of this item type.
     */
    ItemData getItemData();
}
