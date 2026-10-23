package org.allaymc.server.blockentity.component.campfire;

import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.item.recipe.FurnaceRecipe;

/**
 * {@code BlockEntitySoulCampfireBaseComponentImpl} selects soul-campfire
 * recipes for the inherited four-slot cooking implementation. Slot ownership,
 * timers, NBT representation, item drops, and observer updates use the same
 * implementation as ordinary campfires; only recipe-category selection changes.
 *
 * @author daoge_cmd
 */
public class BlockEntitySoulCampfireBaseComponentImpl extends BlockEntityCampfireBaseComponentImpl {

    /**
     * Creates inherited campfire cooking state for the soul-campfire recipe
     * category.
     */
    public BlockEntitySoulCampfireBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns the soul-campfire recipe category used by inherited recipe
     * matching.
     */
    @Override
    public FurnaceRecipe.Type getFurnaceRecipeType() {
        return FurnaceRecipe.Type.SOUL_CAMPFIRE;
    }
}
