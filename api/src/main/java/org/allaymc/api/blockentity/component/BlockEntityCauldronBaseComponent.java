package org.allaymc.api.blockentity.component;

import org.allaymc.api.item.data.PotionType;

import java.awt.*;

/**
 * {@code BlockEntityCauldronBaseComponent} exposes the potion effect, bottle
 * variant, and optional dyed-water color stored for a cauldron. These values
 * supplement the surrounding block properties; callers manage effect identity
 * and bottle variant separately when changing the stored potion.
 *
 * @author daoge_cmd
 */
public interface BlockEntityCauldronBaseComponent extends BlockEntityBaseComponent {

    /**
     * {@code CauldronPotionType} distinguishes drinkable, splash, lingering,
     * and absent potion-container variants independently of the effect
     * identified by {@link PotionType}.
     */
    enum CauldronPotionType {
        /**
         * No stored potion-container variant.
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
     * Returns the stored potion effect, or {@code null} when absent. This value
     * is independent of the stored bottle variant, so callers changing a potion
     * coordinate both selections when needed.
     */
    PotionType getPotionType();

    /**
     * Stores the potion effect independently of the bottle variant and color.
     * {@code null} clears the effect selection, and the server broadcasts the
     * changed block-entity state after storing the reference.
     */
    void setPotionType(PotionType potionType);

    /**
     * Returns the stored bottle variant independently of the potion effect.
     */
    CauldronPotionType getCauldronPotionType();

    /**
     * Stores the potion-container variant and sends updated state to viewers.
     * Callers provide a non-{@code null} variant because the server's NBT
     * encoder switches on the retained value without validating it.
     */
    void setCauldronPotionType(CauldronPotionType type);

    /**
     * Returns whether the stored potion effect reference is non-{@code null}.
     */
    default boolean hasPotion() {
        return getPotionType() != null;
    }

    /**
     * Returns the custom water color, or {@code null} when absent. Potion
     * effect and bottle variant do not determine this value; callers can clear
     * or replace the color independently.
     */
    Color getCustomColor();

    /**
     * Stores the custom water color independently of the potion selections.
     * {@code null} removes the color, and the server broadcasts the changed
     * block-entity state after storing the new reference.
     */
    void setCustomColor(Color color);

    /**
     * Returns whether this cauldron currently stores a custom water color.
     */
    default boolean hasCustomColor() {
        return getCustomColor() != null;
    }

    /**
     * Removes the custom water color through {@code setCustomColor}. Potion
     * effect and bottle variant remain unchanged; the server follows its
     * ordinary color-setter broadcast path for this operation.
     */
    default void clearCustomColor() {
        setCustomColor(null);
    }

    /**
     * Clears potion effect, bottle variant, and custom water color in sequence.
     * Each setter retains its ordinary side effects, so the server can
     * broadcast intermediate states rather than applying one atomic update.
     */
    default void clearPotionData() {
        setPotionType(null);
        setCauldronPotionType(CauldronPotionType.NONE);
        clearCustomColor();
    }

    /**
     * Mixes a supplied dye into the current custom water color. The server
     * adopts the supplied color when none exists; otherwise it blends RGB
     * channel pairs and broadcasts the resulting color.
     */
    void mixColor(Color color);
}
