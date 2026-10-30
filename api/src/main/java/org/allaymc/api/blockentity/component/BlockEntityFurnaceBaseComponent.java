package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.recipe.FurnaceRecipe;

/**
 * {@code BlockEntityFurnaceBaseComponent} exposes furnace processing state,
 * block variants, and recipe-dependent speeds. Burning counters use server
 * ticks, cooking progress advances by the selected speed, and stored experience
 * remains available until the implementation releases it when results are
 * removed or the block is replaced.
 *
 * @author daoge_cmd
 */
public interface BlockEntityFurnaceBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns the remaining fuel-burning duration measured in server ticks.
     */
    int getBurnTime();

    /**
     * Returns accumulated cooking progress toward the next completed furnace
     * operation.
     */
    int getCookTime();

    /**
     * Returns the initial duration of the currently recorded fuel burn.
     */
    int getBurnDuration();

    /**
     * Returns the integer experience accumulated for later furnace experience
     * drops.
     */
    int getStoredXP();

    /**
     * Replaces the integer experience retained for later furnace drops. The
     * implementation stores {@code storedXP} directly; nonpositive values
     * produce no experience orb when the normal release path runs.
     */
    void setStoredXP(int storedXP);

    /**
     * Returns the unlit block type selected for this furnace variant.
     */
    BlockType<?> getUnlitBlockType();

    /**
     * Returns the burning block type selected for this furnace variant.
     */
    BlockType<?> getLitBlockType();

    /**
     * Selects the lit or unlit block type at this furnace's position.
     * Compatible property values are copied from the current block state, and
     * an already matching type requires no replacement.
     */
    void setLit(boolean lit);

    /**
     * Returns the specialized recipe category preferred by this furnace
     * variant.
     */
    FurnaceRecipe.Type getFurnaceRecipeType();

    /**
     * Returns cooking progress per tick for a normal-furnace fallback recipe.
     */
    float getNormalSpeed();

    /**
     * Returns cooking progress per tick for this variant's preferred recipes.
     */
    float getIdealSpeed();

    /**
     * Returns the cooking speed selected from the currently cached recipe. The
     * recipe must already have been matched; the implementation chooses ideal
     * speed for the preferred category and normal speed for a fallback.
     * Processing truncates the selected speed to whole progress units each
     * tick.
     */
    float getCurrentSpeed();
}
