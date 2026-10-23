package org.allaymc.server.item.component.planting;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * {@code ItemWaterlilyBaseComponentImpl} places a waterlily above the water
 * block identified by a player interaction. The inherited placement helper
 * owns placement checks and side effects, while this specialization redirects
 * the target position and requires clicked water context.
 */
public class ItemWaterlilyBaseComponentImpl extends ItemBaseComponentImpl {
    /**
     * Creates waterlily-placement behavior from the supplied item
     * initialization data.
     */
    public ItemWaterlilyBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Attempts to place a waterlily one block above the clicked water
     * position. Missing interaction context or a clicked position without
     * water returns {@code false}; the supplied {@code placeBlockPos} does not
     * choose the final target.
     */
    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null || !isWaterSurface(dimension, placementInfo.clickedBlockPos())) {
            return false;
        }

        var targetPos = new Vector3i(placementInfo.clickedBlockPos()).add(0, 1, 0);
        return tryPlaceBlockState(dimension, BlockTypes.WATERLILY.getDefaultState(), targetPos, placementInfo);
    }

    /**
     * Returns whether the position contains a liquid tagged as water. The
     * dimension lookup can find foreground or contained-layer liquid; this
     * predicate does not separately check source depth or the block above.
     */
    protected boolean isWaterSurface(Dimension dimension, Vector3ic pos) {
        var liquid = dimension.getLiquid(pos).right();
        return liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER);
    }
}
