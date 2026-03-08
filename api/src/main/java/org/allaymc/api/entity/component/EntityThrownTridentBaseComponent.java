package org.allaymc.api.entity.component;

import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.interfaces.ItemTridentStack;

/**
 * Component interface for thrown trident entities.
 *
 * @author daoge_cmd
 */
public interface EntityThrownTridentBaseComponent extends EntityBaseComponent {

    /**
     * Gets the trident item associated with this entity.
     *
     * @return the trident item stack
     */
    ItemTridentStack getTridentItem();

    /**
     * Sets the trident item associated with this entity.
     *
     * @param tridentItem the trident item stack
     */
    void setTridentItem(ItemTridentStack tridentItem);

    /**
     * Gets the favored slot for loyalty return.
     * This is the slot the trident will prefer to return to.
     *
     * @return the favored slot index, or -1 if no loyalty enchantment
     */
    int getFavoredSlot();

    /**
     * Sets the favored slot for loyalty return.
     *
     * @param slot the slot index, or -1 for no preference
     */
    void setFavoredSlot(int slot);

    /**
     * Gets the loyalty enchantment level from the trident item.
     *
     * @return the loyalty level (0 if not enchanted)
     */
    default int getLoyaltyLevel() {
        var item = getTridentItem();
        return item != null ? item.getEnchantmentLevel(EnchantmentTypes.LOYALTY) : 0;
    }

    /**
     * Gets the impaling enchantment level from the trident item.
     *
     * @return the impaling level (0 if not enchanted)
     */
    default int getImpalingLevel() {
        var item = getTridentItem();
        return item != null ? item.getEnchantmentLevel(EnchantmentTypes.IMPALING) : 0;
    }

    /**
     * Checks if the trident has channeling enchantment.
     *
     * @return true if has channeling
     */
    default boolean hasChanneling() {
        var item = getTridentItem();
        return item != null && item.hasEnchantment(EnchantmentTypes.CHANNELING);
    }

    /**
     * Checks if the trident is returning to the shooter.
     *
     * @return true if returning
     */
    boolean isReturning();

    /**
     * Sets whether the trident is returning to the shooter.
     *
     * @param returning true if returning
     */
    void setReturning(boolean returning);
}
