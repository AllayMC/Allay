package org.allaymc.server.blockentity.component.furnace;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.item.recipe.FurnaceRecipe;

/**
 * Allay Project 2024/8/12
 *
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
    public String getFurnaceRecipeTag() {
        return FurnaceRecipe.SMOKER_TAG;
    }

    @Override
    public int getSpeedWhenFurnaceTypeMostSuitable() {
        return 2;
    }
}
