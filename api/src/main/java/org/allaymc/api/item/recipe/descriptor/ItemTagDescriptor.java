package org.allaymc.api.item.recipe.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemTag;

import java.util.Objects;

/**
 * {@code ItemTagDescriptor} matches recipe ingredients through membership in
 * one item tag. The candidate's item type supplies its tags, so stack count,
 * metadata, and NBT do not affect this predicate; descriptor equality and
 * hashing use the stored tag.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class ItemTagDescriptor implements ItemDescriptor {
    /**
     * Item tag required on the candidate type.
     */
    protected ItemTag itemTag;

    /**
     * Returns whether the candidate's item type contains the stored item tag.
     */
    @Override
    public boolean match(ItemStack itemStack) {
        return itemStack.getItemType().hasItemTag(itemTag);
    }

    /**
     * Returns whether another item-tag descriptor holds an equal item tag.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ItemTagDescriptor that)) {
            return false;
        }
        return Objects.equals(itemTag, that.itemTag);
    }

    /**
     * Returns the stored tag hash used by descriptor equality.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(itemTag);
    }
}
