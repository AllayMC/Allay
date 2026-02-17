package org.allaymc.server.item.component.seeds;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class ItemTorchflowerSeedsBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemTorchflowerSeedsBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return tryPlaceBlockState(dimension, BlockTypes.TORCHFLOWER_CROP.getDefaultState(), placeBlockPos, placementInfo);
    }
}
