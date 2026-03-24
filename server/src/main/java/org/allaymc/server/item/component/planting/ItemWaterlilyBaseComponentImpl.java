package org.allaymc.server.item.component.planting;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.joml.Vector3i;
import org.joml.Vector3ic;

public class ItemWaterlilyBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemWaterlilyBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null || !isWaterSurface(dimension, placementInfo.clickedBlockPos())) {
            return false;
        }

        var targetPos = new Vector3i(placementInfo.clickedBlockPos()).add(0, 1, 0);
        return tryPlaceBlockState(dimension, BlockTypes.WATERLILY.getDefaultState(), targetPos, placementInfo);
    }

    protected boolean isWaterSurface(Dimension dimension, Vector3ic pos) {
        var liquid = dimension.getLiquid(pos).right();
        return liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER);
    }
}
