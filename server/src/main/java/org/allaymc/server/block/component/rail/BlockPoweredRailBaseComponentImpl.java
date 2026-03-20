package org.allaymc.server.block.component.rail;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * Component for powered rails (Golden Rail) and activator rails.
 * Cannot curve (orientations 0-5 only). Supports redstone power
 * propagation along chains of the same rail type (up to 8 blocks).
 *
 * @author daoge_cmd
 */
public class BlockPoweredRailBaseComponentImpl extends BlockRailBaseComponentImpl {

    protected static final int MAX_CHAIN_LENGTH = 8;

    public BlockPoweredRailBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType, false, BlockPropertyTypes.RAIL_DIRECTION_6);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        // If the rail was broken by super (support check), skip power logic
        if (block.getDimension().getBlockState(block.getPosition()).getBlockType() != blockType) {
            return;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();
        boolean currentlyPowered = block.getPropertyValue(BlockPropertyTypes.RAIL_DATA_BIT);

        // Check if this rail should be powered
        boolean shouldBePowered = checkPowered(dimension, pos, block.getBlockState());

        if (currentlyPowered != shouldBePowered) {
            // Update the powered state
            dimension.updateBlockProperty(BlockPropertyTypes.RAIL_DATA_BIT, shouldBePowered, pos);

            // Notify neighbors about the power state change
            dimension.updateAround(pos);

            // If ascending, also update around the block below the ascending end
            RailOrientation orientation = RailOrientation.byMeta(getRailDirection(block.getBlockState()));
            if (orientation.isAscending()) {
                BlockFace ascendFace = orientation.ascendingDirection();
                dimension.updateAround(ascendFace.offsetPos(pos));
            }
        }
    }

    /**
     * Checks if this powered rail should be in a powered state.
     * A powered rail is powered if:
     * 1. It receives direct redstone power, OR
     * 2. A connected rail of the same type (along the same axis) within 8 blocks is directly powered.
     */
    protected boolean checkPowered(Dimension dimension, Vector3ic pos, BlockState blockState) {
        // Check direct redstone power
        if (dimension.isPoweredAt(pos)) {
            return true;
        }

        RailOrientation orientation = RailOrientation.byMeta(getRailDirection(blockState));

        // Check chain power in both connecting directions
        for (BlockFace face : orientation.connectingDirections()) {
            if (checkChainPower(dimension, pos, face, orientation, 0)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Recursively checks if a chain of same-type rails leads to a directly powered rail.
     */
    protected boolean checkChainPower(Dimension dimension, Vector3ic pos, BlockFace direction, RailOrientation orientation, int depth) {
        if (depth >= MAX_CHAIN_LENGTH) return false;

        Vector3ic nextPos = direction.offsetPos(pos);
        BlockState nextState = dimension.getBlockState(nextPos);

        // Check for ascending: if current rail ascends toward the direction, check Y+1
        if (orientation.isAscending() && orientation.ascendingDirection() == direction) {
            nextPos = new Vector3i(nextPos.x(), nextPos.y() + 1, nextPos.z());
            nextState = dimension.getBlockState(nextPos);
        }

        // If not a rail of the same type at nextPos, check Y-1 (descending into next)
        if (nextState.getBlockType() != blockType) {
            // Try one block down (rail going downhill)
            Vector3ic downPos = new Vector3i(nextPos.x(), nextPos.y() - 1, nextPos.z());
            BlockState downState = dimension.getBlockState(downPos);
            if (downState.getBlockType() == blockType) {
                nextPos = downPos;
                nextState = downState;
            } else {
                return false;
            }
        }

        if (nextState.getBlockType() != blockType) {
            return false;
        }

        // Verify the next rail is on the same axis (only propagate straight)
        RailOrientation nextOrientation = RailOrientation.byMeta(nextState.getPropertyValue(BlockPropertyTypes.RAIL_DIRECTION_6));
        if (!nextOrientation.hasConnection(direction) && !nextOrientation.hasConnection(direction.opposite())) {
            return false;
        }

        // Check if this next rail is directly powered
        if (dimension.isPoweredAt(nextPos)) {
            return true;
        }

        // Continue chain in the same direction
        return checkChainPower(dimension, nextPos, direction, nextOrientation, depth + 1);
    }
}
