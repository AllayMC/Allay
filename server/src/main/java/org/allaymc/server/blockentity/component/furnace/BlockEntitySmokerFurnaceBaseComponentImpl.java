package org.allaymc.server.blockentity.component.furnace;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.item.recipe.FurnaceRecipe;

/**
 * @author daoge_cmd
 */
public class BlockEntitySmokerFurnaceBaseComponentImpl extends BlockEntityFurnaceBaseComponentImpl {
    public BlockEntitySmokerFurnaceBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public BlockType<?> getLitBlockType() {
        return BlockTypes.LIT_SMOKER;
    }

    @Override
    public BlockType<?> getUnlitBlockType() {
        return BlockTypes.SMOKER;
    }

    @Override
    public FurnaceRecipe.Type getFurnaceRecipeType() {
        return FurnaceRecipe.Type.SMOKER;
    }

    @Override
    public float getIdealSpeed() {
        return 2;
    }
}
