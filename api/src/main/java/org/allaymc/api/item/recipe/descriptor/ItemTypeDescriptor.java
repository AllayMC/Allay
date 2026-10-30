package org.allaymc.api.item.recipe.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;

import java.util.Objects;

/**
 * {@code ItemTypeDescriptor} matches recipe ingredients against one item-type
 * identity and an optional metadata value. {@code WILDCARD_META} accepts any
 * metadata during matching, while other values require equality. Stack count
 * and NBT are ignored, and {@code createItemStack} passes the stored metadata
 * through even when it is the wildcard sentinel.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class ItemTypeDescriptor implements ItemDescriptor {

    /**
     * {@code WILDCARD_META} disables metadata equality during ingredient
     * matching; it remains the stored numeric metadata when a representative
     * stack is created.
     */
    public static final int WILDCARD_META = 32767;

    /**
     * Item-type identity required for matching.
     */
    protected ItemType<?> itemType;
    /**
     * Metadata value or {@code WILDCARD_META} sentinel.
     */
    protected int meta;

    /**
     * Creates a descriptor accepting any metadata for the supplied item type.
     * The stored metadata is {@code WILDCARD_META}, which affects matching but
     * is also passed unchanged to representative stack creation.
     */
    public ItemTypeDescriptor(ItemType<?> itemType) {
        this(itemType, WILDCARD_META);
    }

    /**
     * Returns whether the item-type identity and metadata satisfy this
     * descriptor. {@code WILDCARD_META} skips the metadata check; every other
     * stored value requires equality, while candidate counts and NBT are
     * ignored.
     */
    @Override
    public boolean match(ItemStack itemStack) {
        return itemStack.getItemType() == itemType && (isWildcard() || itemStack.getMeta() == meta);
    }

    /**
     * Creates a count-one representative stack using the stored type and
     * metadata. The wildcard sentinel is passed directly to
     * {@code ItemType.createItemStack}, so wildcard descriptors do not
     * substitute a concrete metadata variant.
     */
    public ItemStack createItemStack() {
        return itemType.createItemStack(1, meta);
    }

    /**
     * Returns whether the stored metadata equals the wildcard matching
     * sentinel.
     */
    public boolean isWildcard() {
        return meta == WILDCARD_META;
    }

    /**
     * Returns the namespaced item identifier followed by the stored metadata.
     */
    @Override
    public String toString() {
        return itemType.getIdentifier() + ":" + meta;
    }

    /**
     * Returns whether another descriptor has equal item-type and metadata
     * values.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ItemTypeDescriptor that)) {
            return false;
        }
        return meta == that.meta && Objects.equals(itemType, that.itemType);
    }

    /**
     * Returns the combined hash of item type and stored metadata.
     */
    @Override
    public int hashCode() {
        return Objects.hash(itemType, meta);
    }
}
