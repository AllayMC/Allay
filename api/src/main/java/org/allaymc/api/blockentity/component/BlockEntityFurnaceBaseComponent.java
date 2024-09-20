package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.type.BlockType;

/**
 * @author daoge_cmd
 */
public interface BlockEntityFurnaceBaseComponent extends BlockEntityBaseComponent {
    /**
     * Gets the burn time.
     *
     * @return the burn time
     */
    int getBurnTime();

    /**
     * Gets the cook time.
     *
     * @return the cook time
     */
    int getCookTime();

    /**
     * Gets the burn duration.
     *
     * @return the burn duration
     */
    int getBurnDuration();

    /**
     * Gets the stored XP.
     *
     * @return the stored XP
     */
    float getStoredXP();

    /**
     * Sets the stored XP.
     *
     * @param storedXP the stored XP to set
     */
    void setStoredXP(float storedXP);

    /**
     * Gets the unlit block type.
     *
     * @return the unlit block type
     */
    BlockType<?> getUnlitBlockType();

    /**
     * Gets the lit block type.
     *
     * @return the lit block type
     */
    BlockType<?> getLitBlockType();

    /**
     * Sets whether the furnace is lit.
     *
     * @param lit {@code true} if the furnace is lit, {@code false} otherwise
     */
    void setLit(boolean lit);

    /**
     * Gets the furnace recipe tag.
     *
     * @return the furnace recipe tag
     */
    String getFurnaceRecipeTag();

    /**
     * Gets the normal speed.
     *
     * @return the normal speed
     */
    float getNormalSpeed();

    /**
     * Gets the ideal speed.
     *
     * @return the ideal speed
     */
    float getIdealSpeed();

    /**
     * Gets the current speed.
     *
     * @return the current speed
     */
    float getCurrentSpeed();
}
