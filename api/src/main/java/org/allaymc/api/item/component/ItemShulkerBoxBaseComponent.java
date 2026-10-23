package org.allaymc.api.item.component;

import org.allaymc.api.item.ItemStack;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * {@code ItemShulkerBoxBaseComponent} exposes the slot-indexed contents carried by
 * a shulker-box item. Extra-tag persistence carries those contents between item
 * and placed-container forms. Map containers protect their own structure, but the
 * stored item stack values remain shared references.
 *
 * @author daoge_cmd
 */
public interface ItemShulkerBoxBaseComponent extends ItemBaseComponent {
    /**
     * Returns an unmodifiable view of slot indices and stored item stacks.
     * The view prevents structural edits through the map, but its item values are
     * shared with the component and remain mutable.
     */
    @UnmodifiableView
    Map<Integer, ItemStack> getStoredItems();

    /**
     * Replaces the slot-indexed contents using the nonempty, non-air entries.
     * The component copies the map structure and retains the item values, so callers
     * can edit their map without replacing the stored mapping.
     */
    void setStoredItems(Map<Integer, ItemStack> items);
}
