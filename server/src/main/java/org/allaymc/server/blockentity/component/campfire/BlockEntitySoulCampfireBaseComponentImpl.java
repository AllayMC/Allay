package org.allaymc.server.blockentity.component.campfire;

import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.item.recipe.FurnaceRecipe;

/**
 * @author daoge_cmd
 */
public class BlockEntitySoulCampfireBaseComponentImpl extends BlockEntityCampfireBaseComponentImpl {

    public BlockEntitySoulCampfireBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public FurnaceRecipe.Type getFurnaceRecipeType() {
        return FurnaceRecipe.Type.SOUL_CAMPFIRE;
    }
}
