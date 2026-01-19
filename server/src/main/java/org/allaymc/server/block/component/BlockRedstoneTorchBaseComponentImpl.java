package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.TorchFacingDirection;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.TORCH_FACING_DIRECTION;

/**
 * Implementation of the redstone torch block.
 * Outputs redstone signal when lit. Turns off when the attached block is powered.
 *
 * @author daoge_cmd
 */
public class BlockRedstoneTorchBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final Duration UPDATE_DELAY = Duration.ofMillis(100); // 2 ticks

    protected final boolean lit;

    public BlockRedstoneTorchBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean lit) {
        super(blockType);
        this.lit = lit;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            TorchFacingDirection facing = calculateTorchFacing(placementInfo);
            blockState = blockState.setPropertyValue(TORCH_FACING_DIRECTION, facing);

            // Verify attachment block is solid
            BlockFace attachedFace = getAttachedFace(facing);
            Vector3ic attachedPos = attachedFace.offsetPos(placeBlockPos);
            BlockState attachedState = dimension.getBlockState(attachedPos);
            if (!attachedState.getBlockStateData().isSolid()) {
                return false;
            }
        }

        if (!dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo)) {
            return false;
        }

        // Trigger updates to propagate power
        TorchFacingDirection facing = blockState.getPropertyValue(TORCH_FACING_DIRECTION);
        updateSecondOrderNeighbors(dimension, placeBlockPos, facing);
        return true;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        TorchFacingDirection facing = block.getPropertyValue(TORCH_FACING_DIRECTION);
        BlockFace attachedFace = getAttachedFace(facing);

        // Break if attached block is removed
        if (face == attachedFace && !neighbor.getBlockStateData().isSolid()) {
            block.breakBlock();
            return;
        }

        // Check if attached block is now powered/unpowered
        // Schedule update to prevent rapid flickering
        block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(
                block.getPosition(), UPDATE_DELAY
        );
    }

    @Override
    public void onScheduledUpdate(Block block) {
        boolean attachedPowered = isAttachedBlockPowered(block);

        if (lit && attachedPowered) {
            // Turn off
            switchState(block, false);
        } else if (!lit && !attachedPowered) {
            // Turn on
            switchState(block, true);
        }
    }

    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);

        // When removed, update neighbors
        var dimension = oldBlock.getDimension();
        var pos = oldBlock.getPosition();
        TorchFacingDirection facing = oldBlock.getPropertyValue(TORCH_FACING_DIRECTION);
        updateSecondOrderNeighbors(dimension, pos, facing);
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        if (!lit) {
            return 0;
        }

        // Don't power the block it's attached to
        TorchFacingDirection facing = block.getPropertyValue(TORCH_FACING_DIRECTION);
        BlockFace attachedFace = getAttachedFace(facing);
        if (face == attachedFace) {
            return 0;
        }

        return MAX_REDSTONE_POWER;
    }

    @Override
    public int getStrongPower(Block block, BlockFace face) {
        if (!lit) {
            return 0;
        }

        // Strong power only upward (to block above)
        // face is the direction the query comes FROM, so UP means the block above is querying
        if (face == BlockFace.UP) {
            return MAX_REDSTONE_POWER;
        }
        return 0;
    }

    @Override
    public boolean isPowerSource() {
        return true;
    }

    /**
     * Checks if the block the torch is attached to is receiving power.
     */
    protected boolean isAttachedBlockPowered(Block block) {
        TorchFacingDirection facing = block.getPropertyValue(TORCH_FACING_DIRECTION);
        BlockFace attachedFace = getAttachedFace(facing);
        Vector3ic attachedPos = attachedFace.offsetPos(block.getPosition());

        var dimension = block.getDimension();
        BlockState attachedState = dimension.getBlockState(attachedPos);

        // Check if the attached block is receiving power from any source except this torch
        return isPoweredExcluding(dimension, attachedPos, block.getPosition());
    }

    /**
     * Checks if a block position is receiving power, excluding a specific source position.
     */
    protected boolean isPoweredExcluding(Dimension dimension, Vector3ic blockPos, Vector3ic excludePos) {
        for (BlockFace face : BlockFace.VALUES) {
            Vector3ic neighborPos = face.offsetPos(blockPos);

            // Skip the excluded position (the torch itself)
            if (neighborPos.equals(excludePos)) {
                continue;
            }

            BlockState neighborState = dimension.getBlockState(neighborPos);
            Block neighborBlock = new Block(neighborState, new Position3i(neighborPos, dimension));

            // Check weak power
            int weakPower = neighborState.getBehavior().getWeakPower(neighborBlock, face.opposite());
            if (weakPower > 0) {
                return true;
            }

            // Check power through solid blocks (only strong power can conduct through)
            if (neighborState.getBlockStateData().isSolid()) {
                int powerThroughBlock = getStrongPowerIntoBlockExcluding(dimension, neighborPos, excludePos, face.opposite());
                if (powerThroughBlock > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets strong power flowing into a solid block, excluding specific positions.
     * Only strong power can be conducted through solid blocks.
     *
     * @param dimension   the dimension
     * @param blockPos    the solid block position
     * @param excludePos  position to exclude (the torch itself)
     * @param excludeFace face to exclude (pointing back to the torch)
     */
    protected int getStrongPowerIntoBlockExcluding(Dimension dimension, Vector3ic blockPos, Vector3ic excludePos, BlockFace excludeFace) {
        int maxPower = 0;
        for (BlockFace face : BlockFace.VALUES) {
            if (face == excludeFace) continue;

            Vector3ic checkPos = face.offsetPos(blockPos);
            if (checkPos.equals(excludePos)) {
                continue;
            }

            BlockState state = dimension.getBlockState(checkPos);

            // Skip redstone wire - wire's "strong power" to a solid block should not
            // propagate to adjacent blocks and cause torch to turn off.
            // This matches vanilla behavior where wire charging a block doesn't
            // indirectly power adjacent blocks for torch detection.
            if (state.getBlockType() == BlockTypes.REDSTONE_WIRE) {
                continue;
            }

            Block checkBlock = new Block(state, new Position3i(checkPos, dimension));

            // Only strong power can be conducted through solid blocks
            int strongPower = state.getBehavior().getStrongPower(checkBlock, face.opposite());
            maxPower = Math.max(maxPower, strongPower);
        }
        return maxPower;
    }

    protected void switchState(Block block, boolean newLit) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        TorchFacingDirection facing = block.getPropertyValue(TORCH_FACING_DIRECTION);

        BlockType<?> newType = newLit ? BlockTypes.REDSTONE_TORCH : BlockTypes.UNLIT_REDSTONE_TORCH;
        BlockState newState = newType.getDefaultState().setPropertyValue(TORCH_FACING_DIRECTION, facing);

        dimension.setBlockState(pos.x(), pos.y(), pos.z(), newState);

        updateSecondOrderNeighbors(dimension, pos, facing);
    }

    /**
     * Updates second-order neighbors when torch state changes.
     * This includes neighbors of the attached block and the block above (which receives strong power).
     */
    protected void updateSecondOrderNeighbors(Dimension dimension, Vector3ic pos, TorchFacingDirection facing) {
        // Update direct neighbors
        dimension.updateAround(pos);
        // Update around the attached block (second-order neighbors)
        BlockFace attachedFace = getAttachedFace(facing);
        dimension.updateAround(attachedFace.offsetPos(pos));
        // Update around the block above (receives strong power from torch)
        dimension.updateAround(BlockFace.UP.offsetPos(pos));
    }

    protected TorchFacingDirection calculateTorchFacing(PlayerInteractInfo placementInfo) {
        BlockFace clickedFace = placementInfo.blockFace();

        // Torch facing direction is opposite to the clicked face
        // (torch points away from the block it's attached to)
        return switch (clickedFace) {
            case UP -> TorchFacingDirection.TOP;
            case DOWN -> TorchFacingDirection.TOP; // Can't place on ceiling, default to floor
            case NORTH -> TorchFacingDirection.SOUTH;
            case SOUTH -> TorchFacingDirection.NORTH;
            case WEST -> TorchFacingDirection.EAST;
            case EAST -> TorchFacingDirection.WEST;
        };
    }

    protected BlockFace getAttachedFace(TorchFacingDirection facing) {
        // TorchFacingDirection indicates which direction the wall is (relative to torch)
        return switch (facing) {
            case TOP, UNKNOWN -> BlockFace.DOWN; // Attached to floor (block below)
            case WEST -> BlockFace.WEST;   // Attached to west wall
            case EAST -> BlockFace.EAST;   // Attached to east wall
            case NORTH -> BlockFace.NORTH; // Attached to north wall
            case SOUTH -> BlockFace.SOUTH; // Attached to south wall
        };
    }
}
