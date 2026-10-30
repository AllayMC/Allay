package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftCorner;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.BlockPlaceHelper;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;
import static org.allaymc.server.block.BlockPlaceHelper.EWSN_DIRECTION_4_MAPPER;

/**
 * {@code BlockStairsBaseComponentImpl} selects stair orientation and derives
 * corner states from horizontal neighbors. Player placement determines facing
 * and the occupied half before corner selection. Placements without player
 * context retain their supplied orientation, and direct dimension placements
 * receive the same corner calculation through the postplacement callback.
 *
 * <p>
 * Only tagged stairs in the same half can form a corner. A perpendicular stair
 * ahead can form an outer corner; one behind can form an inner corner unless
 * an outer corner already qualifies. A parallel stair alongside the proposed
 * bend can prevent that corner. Neighbor updates preserve all other properties
 * and publish only changed corners, so reciprocal notifications settle without
 * repeatedly writing identical states. Collision and liquid-flow checks then
 * use the metadata for the selected corner state.
 *
 * @author daoge_cmd
 */
public class BlockStairsBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the stairs block behavior for the supplied block type.
     */
    public BlockStairsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places stairs after selecting orientation and the corner formed by neighbors.
     * A {@code null} {@code placementInfo} retains the supplied facing and half.
     * The result reports whether the dimension accepts the calculated state.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            var stairFace = placementInfo.player().getHorizontalFace();
            blockState = blockState.setPropertyValue(WEIRDO_DIRECTION, EWSN_DIRECTION_4_MAPPER.get(stairFace));
            blockState = BlockPlaceHelper.processUpsideDownBitProperty(blockState, placeBlockPos, placementInfo);
        }

        var block = new Block(blockState, new Position3i(placeBlockPos, dimension));
        blockState = blockState.setPropertyValue(MINECRAFT_CORNER, calculateCorner(block));
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Forwards the placement callback and refreshes the stored stair's corner.
     * Direct dimension writes also reach this path; already calculated placement
     * states require no additional write or neighbor notifications.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        updateCorner(new Block(newBlockState, oldBlock.getPosition(), oldBlock.getLayer()));
    }

    /**
     * Forwards neighbor notifications and recalculates corners for horizontal changes.
     * Vertical neighbors cannot affect stair corners. A changed corner sends client
     * and neighbor updates while retaining the stair's orientation and storage layer.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face.getAxis() != BlockFace.Axis.Y) {
            updateCorner(block);
        }
    }

    /**
     * Writes the calculated corner only when it differs from the supplied snapshot.
     * The write preserves other properties and notifies clients and neighbors;
     * placement and replacement callbacks are disabled for this derived-state update.
     */
    private void updateCorner(Block block) {
        var state = block.getBlockState();
        var corner = calculateCorner(block);
        if (state.getPropertyValue(MINECRAFT_CORNER) != corner) {
            // A corner change is not a new placement. Notify neighboring blocks
            // without reentering placement callbacks or continuously rewriting stable corners.
            block.getDimension().setBlockState(block.getPosition(), state.setPropertyValue(MINECRAFT_CORNER, corner),
                    block.getLayer(), true, true, false);
        }
    }

    /**
     * Selects a corner from the stair's facing, half, and horizontal neighbors.
     * Perpendicular stairs must occupy the same half; their material and existing
     * corner do not affect the choice. The facing points toward the stair's full side.
     *
     * <p>
     * The front neighbor is considered before the back neighbor, giving outer
     * corners priority over inner corners. A same-facing, same-half continuation
     * on the candidate's blocking side preserves a straight run instead of bending
     * it. If neither candidate qualifies, the result is {@code MinecraftCorner.NONE}.
     */
    private MinecraftCorner calculateCorner(Block block) {
        var state = block.getBlockState();
        var facing = getFacing(state);
        var front = block.offsetPos(facing).getBlockState();
        if (isStairInSameHalf(front, state)) {
            var frontFacing = getFacing(front);
            if (frontFacing.getAxis() != facing.getAxis() &&
                !continuesRun(block.offsetPos(frontFacing.opposite()).getBlockState(), state)) {
                return frontFacing == facing.rotateYCCW() ? MinecraftCorner.OUTER_LEFT : MinecraftCorner.OUTER_RIGHT;
            }
        }

        var back = block.offsetPos(facing.opposite()).getBlockState();
        if (isStairInSameHalf(back, state)) {
            var backFacing = getFacing(back);
            if (backFacing.getAxis() != facing.getAxis() &&
                !continuesRun(block.offsetPos(backFacing).getBlockState(), state)) {
                return backFacing == facing.rotateYCCW() ? MinecraftCorner.INNER_LEFT : MinecraftCorner.INNER_RIGHT;
            }
        }
        return MinecraftCorner.NONE;
    }

    /**
     * Returns whether a tagged stair uses compatible orientation properties and
     * occupies the same half. Property identity is checked before reading values,
     * so custom tags alone cannot make an incompatible block participate.
     */
    private boolean isStairInSameHalf(BlockState neighbor, BlockState current) {
        var type = neighbor.getBlockType();
        return type.hasBlockTag(BlockTags.CORNERABLE_STAIRS) &&
               type.getProperties().get(WEIRDO_DIRECTION.getName()) == WEIRDO_DIRECTION &&
               type.getProperties().get(UPSIDE_DOWN_BIT.getName()) == UPSIDE_DOWN_BIT &&
               neighbor.getPropertyValue(UPSIDE_DOWN_BIT).equals(current.getPropertyValue(UPSIDE_DOWN_BIT));
    }

    /**
     * Returns whether the neighboring stair continues the current facing and half.
     */
    private boolean continuesRun(BlockState neighbor, BlockState current) {
        return isStairInSameHalf(neighbor, current) && getFacing(neighbor) == getFacing(current);
    }

    /**
     * Returns the full-side direction using the shared stair orientation encoding.
     */
    private BlockFace getFacing(BlockState state) {
        return EWSN_DIRECTION_4_MAPPER.inverse().get(state.getPropertyValue(WEIRDO_DIRECTION));
    }

    /**
     * Returns whether the collision shape leaves the requested face less than full.
     * Liquid can flow through such faces, while a completely filled face closes that
     * direction to liquid movement.
     */
    @Override
    public boolean canLiquidFlowIntoSide(BlockState blockState, BlockFace blockFace) {
        return !blockState.getBlockStateData().collisionShape().isFull(blockFace);
    }
}
