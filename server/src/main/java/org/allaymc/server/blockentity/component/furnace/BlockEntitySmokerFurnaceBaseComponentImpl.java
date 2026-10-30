package org.allaymc.server.blockentity.component.furnace;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.world.sound.SimpleSound;

/**
 * {@code BlockEntitySmokerFurnaceBaseComponentImpl} supplies the smoker variant
 * of the shared furnace processor. Its overrides select the corresponding block
 * types, recipe category, and crackle sound, while preferred recipes advance at
 * twice the ordinary furnace speed.
 *
 * @author daoge_cmd
 */
public class BlockEntitySmokerFurnaceBaseComponentImpl extends BlockEntityFurnaceBaseComponentImpl {
    /**
     * Creates smoker base state using the supplied initialization information.
     */
    public BlockEntitySmokerFurnaceBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns {@code BlockTypes.LIT_SMOKER} as the burning block variant for
     * this smoker.
     */
    @Override
    public BlockType<?> getLitBlockType() {
        return BlockTypes.LIT_SMOKER;
    }

    /**
     * Returns {@code BlockTypes.SMOKER} as the inactive block variant for this
     * smoker.
     */
    @Override
    public BlockType<?> getUnlitBlockType() {
        return BlockTypes.SMOKER;
    }

    /**
     * Returns {@code FurnaceRecipe.Type.SMOKER} as the recipe category
     * preferred by this smoker.
     */
    @Override
    public FurnaceRecipe.Type getFurnaceRecipeType() {
        return FurnaceRecipe.Type.SMOKER;
    }

    /**
     * Returns two cooking-progress units per tick for preferred smoker recipes.
     */
    @Override
    public float getIdealSpeed() {
        return 2;
    }

    /**
     * Returns {@code SimpleSound.SMOKER_CRACKLE} for randomly scheduled burning
     * sound effects.
     */
    @Override
    protected SimpleSound getCrackleSound() {
        return SimpleSound.SMOKER_CRACKLE;
    }
}
