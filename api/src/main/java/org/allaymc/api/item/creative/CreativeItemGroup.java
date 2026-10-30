package org.allaymc.api.item.creative;

import org.allaymc.api.item.ItemStack;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * {@code CreativeItemGroup} groups creative entries under a category and a
 * registry-wide group index. A nonempty name identifies a collapsible group with
 * an icon; an empty name identifies an unnamed group whose entries appear without
 * a named heading.
 *
 * @author daoge_cmd
 */
public interface CreativeItemGroup {
    /**
     * Registers the supplied stack reference and returns its indexed creative entry.
     * Registration assigns the stack's unique ID to the entry index plus one; callers
     * must account for this mutation. Registration fails after the registry freezes.
     */
    CreativeItemEntry registerItem(ItemStack itemStack);

    /**
     * Returns an unmodifiable view of this group's registry-indexed creative entries.
     * The map reflects later additions, and each entry retains its registered item
     * stack reference rather than a copied stack.
     */
    @UnmodifiableView
    Map<Integer, CreativeItemEntry> getItems();

    /**
     * Returns this group's registered display name or translation key.
     * An empty string identifies an unnamed group, so {@code isUnnamedGroup} uses
     * emptiness rather than whitespace or translated display text.
     */
    String getName();

    /**
     * Returns the registered stack reference used as this group's icon.
     * The icon is not copied by the accessor; unnamed groups created through the
     * category helper use the shared air stack.
     */
    ItemStack getIcon();

    /**
     * Returns the registry-wide zero-based index identifying this creative group.
     */
    int getIndex();

    /**
     * Returns the creative category that owns this group instance.
     */
    CreativeItemCategory getCategory();

    /**
     * Returns whether this group's registered name is the empty string.
     */
    default boolean isUnnamedGroup() {
        return getName().isEmpty();
    }
}