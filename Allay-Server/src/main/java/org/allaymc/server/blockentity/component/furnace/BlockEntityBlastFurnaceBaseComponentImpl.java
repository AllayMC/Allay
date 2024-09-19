package org.allaymc.server.blockentity.component.furnace;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
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
    public String getFurnaceRecipeTag() {
        return FurnaceRecipe.BLAST_FURNACE_TAG;
    }

    @Override
    public float getIdealSpeed() {
        return 2;
    }
}
