package org.allaymc.api.entity.component;

import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.interfaces.ItemTridentStack;

/**
 * {@code EntityThrownTridentBaseComponent} exposes the item carried by a
 * thrown trident and its loyalty return state. Enchantment helpers read the
 * current item, while return handling uses the preferred inventory slot and
 * returning flag to guide recovery by the shooter.
 *
 * @author daoge_cmd
 */
public interface EntityThrownTridentBaseComponent extends EntityBaseComponent {

    /**
     * Returns the shared carried trident stack, or {@code null} when it has
     * been cleared. Enchantment helpers read this same object, and mutations
     * affect later impact or return handling.
     */
    ItemTridentStack getTridentItem();

    /**
     * Retains the supplied trident stack without copying its data. Passing
     * {@code null} clears the item and makes the enchantment helpers report no
     * Loyalty, Impaling, or Channeling enchantment.
     */
    void setTridentItem(ItemTridentStack tridentItem);

    /**
     * Returns the preferred shooter inventory slot for loyalty return. The
     * value {@code -1} represents no slot preference, independently of whether
     * the carried item currently has a Loyalty enchantment.
     */
    int getFavoredSlot();

    /**
     * Stores the preferred shooter inventory slot for loyalty return. Passing
     * {@code -1} clears the preference; a nonnegative slot must be valid for
     * the shooter inventory before return handling accesses it.
     */
    void setFavoredSlot(int slot);

    /**
     * Returns the carried item Loyalty level, or {@code 0} when no item is
     * assigned. The helper reads the current item directly, so replacing it
     * changes subsequent results.
     */
    default int getLoyaltyLevel() {
        var item = getTridentItem();
        return item != null ? item.getEnchantmentLevel(EnchantmentTypes.LOYALTY) : 0;
    }

    /**
     * Returns the carried item Impaling level, or {@code 0} when no item is
     * assigned. The helper reads the current item directly, so replacing it
     * changes subsequent results.
     */
    default int getImpalingLevel() {
        var item = getTridentItem();
        return item != null ? item.getEnchantmentLevel(EnchantmentTypes.IMPALING) : 0;
    }

    /**
     * Returns whether the current carried item has a Channeling enchantment. A
     * missing item produces {@code false}, allowing impact handling to query
     * this helper after the item has been cleared.
     */
    default boolean hasChanneling() {
        var item = getTridentItem();
        return item != null && item.hasEnchantment(EnchantmentTypes.CHANNELING);
    }

    /**
     * Returns whether the trident is marked as returning to its shooter.
     */
    boolean isReturning();

    /**
     * Changes the returning flag and publishes the updated trident state.
     * Return physics uses this flag to select homing motion, while collision
     * handling suppresses ordinary outbound impacts during return.
     */
    void setReturning(boolean returning);
}
