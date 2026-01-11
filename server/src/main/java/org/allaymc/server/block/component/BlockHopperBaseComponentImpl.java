package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author ClexaGod | daoge_cmd
 */
public class BlockHopperBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockHopperBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var processedState = processBlockProperties(blockState, placeBlockPos, placementInfo);
        var facing = placementInfo.blockFace().opposite();
        if (facing == BlockFace.UP) {
            facing = BlockFace.DOWN;
        }

        processedState = processedState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, facing.ordinal());
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), processedState, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        var powered = block.isPowered();
        var currentlyDisabled = block.getPropertyValue(BlockPropertyTypes.TOGGLE_BIT);

        if (powered != currentlyDisabled) {
            block.updateBlockProperty(BlockPropertyTypes.TOGGLE_BIT, powered);
        }
    }
}
