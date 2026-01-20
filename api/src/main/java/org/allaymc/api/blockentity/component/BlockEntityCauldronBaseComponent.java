package org.allaymc.api.blockentity.component;

import org.allaymc.api.item.data.PotionType;

import java.awt.Color;

/**
 * Component interface for cauldron block entities.
 * <p>
 * Cauldron block entities store potion type and custom color data
 * that cannot be represented by block properties alone.
 *
 * @author daoge_cmd
 */
public interface BlockEntityCauldronBaseComponent extends BlockEntityBaseComponent {

    /**
     * Represents the type of potion container in a cauldron.
     * <p>
     * This enum distinguishes between different potion variants that can be
     * stored in a cauldron: normal potions, splash potions, and lingering potions.
     * Each type corresponds to a different bottle type when extracted.
     * <p>
     * Note: This is different from {@link PotionType}
     * which represents the actual potion effect (e.g., healing, swiftness).
     */
    enum CauldronPotionType {
        /**
         * No potion stored - the cauldron contains plain water.
         */
        NONE,
        /**
         * Normal drinkable potion.
         */
        NORMAL,
        /**
         * Splash potion that can be thrown.
         */
        SPLASH,
        /**
         * Lingering potion that creates an area effect cloud.
         */
        LINGERING
    }

    /**
     * Gets the potion type stored in the cauldron.
     *
     * @return the potion type, or {@code null} if no potion
     */
    PotionType getPotionType();

    /**
     * Sets the potion type.
     *
     * @param potionType the potion type, or {@code null} to clear
     */
    void setPotionType(PotionType potionType);

    /**
     * Gets the cauldron potion type (container type).
     *
     * @return the cauldron potion type
     */
    CauldronPotionType getCauldronPotionType();

    /**
     * Sets the cauldron potion type (container type).
     *
     * @param type the cauldron potion type
     */
    void setCauldronPotionType(CauldronPotionType type);

    /**
     * Checks if the cauldron contains a potion.
     *
     * @return {@code true} if contains potion
     */
    default boolean hasPotion() {
        return getPotionType() != null;
    }

    /**
     * Gets the custom color of the water in the cauldron.
     *
     * @return the custom color, or {@code null} if no custom color
     */
    Color getCustomColor();

    /**
     * Sets the custom color of the water.
     *
     * @param color the color, or {@code null} to clear
     */
    void setCustomColor(Color color);

    /**
     * Checks if the cauldron has a custom color.
     *
     * @return {@code true} if has custom color
     */
    default boolean hasCustomColor() {
        return getCustomColor() != null;
    }

    /**
     * Clears the custom color.
     */
    default void clearCustomColor() {
        setCustomColor(null);
    }

    /**
     * Clears all potion and color data.
     */
    default void clearPotionData() {
        setPotionType(null);
        setCauldronPotionType(CauldronPotionType.NONE);
        clearCustomColor();
    }

    /**
     * Mixes a new dye color with the existing color.
     *
     * @param color the color of the dye to mix
     */
    void mixColor(Color color);
}
