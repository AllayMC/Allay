package org.allaymc.server.blockentity.component.furnace;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.item.recipe.FurnaceRecipe;

/**
 * @author daoge_cmd
 */
public class BlockEntityBlastFurnaceBaseComponentImpl extends BlockEntityFurnaceBaseComponentImpl {
    public BlockEntityBlastFurnaceBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public BlockType<?> getLitBlockType() {
        return BlockTypes.LIT_BLAST_FURNACE;
    }

    @Override
    public BlockType<?> getUnlitBlockType() {
        return BlockTypes.BLAST_FURNACE;
    }

    @Override
    public FurnaceRecipe.Type getFurnaceRecipeType() {
        return FurnaceRecipe.Type.BLAST_FURNACE;
    }

    @Override
    public float getIdealSpeed() {
        return 2;
    }
}
