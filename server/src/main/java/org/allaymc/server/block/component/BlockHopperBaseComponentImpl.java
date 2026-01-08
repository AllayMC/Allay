package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.math.position.Position3i;
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

        boolean powered = isReceivingPower(block);
        boolean currentlyDisabled = block.getPropertyValue(BlockPropertyTypes.TOGGLE_BIT);

        if (powered != currentlyDisabled) {
            block.getDimension().updateBlockProperty(BlockPropertyTypes.TOGGLE_BIT, powered, block.getPosition());
        }
    }

    /**
     * Checks if the hopper is receiving redstone power from any direction.
     */
    protected boolean isReceivingPower(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        for (BlockFace face : BlockFace.values()) {
            Vector3ic neighborPos = face.offsetPos(pos);
            BlockState neighborState = dimension.getBlockState(neighborPos);
            Block neighborBlock = new Block(neighborState, new Position3i(neighborPos, dimension));

            // Check weak power
            int weakPower = neighborState.getBehavior().getWeakPower(neighborBlock, face.opposite());
            if (weakPower > 0) {
                return true;
            }

            // Check power through solid blocks (only strong power can conduct through)
            if (neighborState.getBlockStateData().isOpaqueSolid()) {
                int powerThroughBlock = getStrongPowerIntoBlock(dimension, neighborPos, face.opposite());
                if (powerThroughBlock > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets strong power flowing into a solid block.
     */
    protected int getStrongPowerIntoBlock(Dimension dimension, Vector3ic blockPos, BlockFace excludeFace) {
        int maxPower = 0;
        for (BlockFace face : BlockFace.values()) {
            if (face == excludeFace) continue;

            Vector3ic checkPos = face.offsetPos(blockPos);
            BlockState state = dimension.getBlockState(checkPos);
            Block checkBlock = new Block(state, new Position3i(checkPos, dimension));

            int strongPower = state.getBehavior().getStrongPower(checkBlock, face.opposite());
            maxPower = Math.max(maxPower, strongPower);
        }
        return maxPower;
    }
}
