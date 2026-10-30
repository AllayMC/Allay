package org.allaymc.server.blockentity.component.furnace;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.world.sound.SimpleSound;

/**
 * {@code BlockEntityBlastFurnaceBaseComponentImpl} supplies the blast furnace
 * variant of the shared furnace processor. Its overrides select the
 * corresponding block types, recipe category, and crackle sound, while
 * preferred recipes advance at twice the ordinary furnace speed.
 *
 * @author daoge_cmd
 */
public class BlockEntityBlastFurnaceBaseComponentImpl extends BlockEntityFurnaceBaseComponentImpl {
    /**
     * Creates blast furnace base state using the supplied initialization
     * information.
     */
    public BlockEntityBlastFurnaceBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns {@code BlockTypes.LIT_BLAST_FURNACE} as the burning block variant
     * for this blast furnace.
     */
    @Override
    public BlockType<?> getLitBlockType() {
        return BlockTypes.LIT_BLAST_FURNACE;
    }

    /**
     * Returns {@code BlockTypes.BLAST_FURNACE} as the inactive block variant
     * for this blast furnace.
     */
    @Override
    public BlockType<?> getUnlitBlockType() {
        return BlockTypes.BLAST_FURNACE;
    }

    /**
     * Returns {@code FurnaceRecipe.Type.BLAST_FURNACE} as the recipe category
     * preferred by this blast furnace.
     */
    @Override
    public FurnaceRecipe.Type getFurnaceRecipeType() {
        return FurnaceRecipe.Type.BLAST_FURNACE;
    }

    /**
     * Returns two cooking-progress units per tick for preferred blast furnace
     * recipes.
     */
    @Override
    public float getIdealSpeed() {
        return 2;
    }

    /**
     * Returns {@code SimpleSound.BLAST_FURNACE_CRACKLE} for randomly scheduled
     * burning sound effects.
     */
    @Override
    protected SimpleSound getCrackleSound() {
        return SimpleSound.BLAST_FURNACE_CRACKLE;
    }
}
