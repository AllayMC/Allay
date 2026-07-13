package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * Allay Project 2026/7/12
 *
 * @author Miroshka000
 */
public class BlockBubbleColumnBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockBubbleColumnBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        updateAt(oldBlock.getDimension(), oldBlock.getPosition());
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        updateAt(block.getDimension(), block.getPosition());
    }

    @Override
    public void afterNeighborLayerReplace(Block currentBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterNeighborLayerReplace(currentBlock, newBlockState, placementInfo);
        updateAt(currentBlock.getDimension(), currentBlock.getPosition());
    }

    public static void updateAbove(Block block) {
        updateAt(block.getDimension(), BlockFace.UP.offsetPos(block.getPosition()));
    }

    public static void updateAt(Dimension dimension, Vector3ic position) {
        var cursor = new Vector3i(position);
        var direction = getDirection(dimension, BlockFace.DOWN.offsetPos(cursor));

        while (dimension.isYInRange(cursor.y())) {
            var current = dimension.getBlockState(cursor);
            if (direction == null) {
                if (current.getBlockType() != BlockTypes.BUBBLE_COLUMN || !restoreWater(dimension, cursor)) {
                    return;
                }

                cursor.add(0, 1, 0);
                continue;
            }

            if (current.getBlockType() == BlockTypes.BUBBLE_COLUMN) {
                if (!isSourceWater(dimension.getBlockState(cursor, 1))) {
                    if (!restoreWater(dimension, cursor)) {
                        return;
                    }
                    direction = null;
                    cursor.add(0, 1, 0);
                    continue;
                }

                if (current.getPropertyValue(BlockPropertyTypes.DRAG_DOWN) != direction.booleanValue() &&
                    !setBubbleColumnDirection(dimension, cursor, current, direction)) {
                    return;
                }

                cursor.add(0, 1, 0);
                continue;
            }

            if (!isSourceWater(current) || !replaceWithBubbleColumn(dimension, cursor, current, direction)) {
                return;
            }
            cursor.add(0, 1, 0);
        }
    }

    private static Boolean getDirection(Dimension dimension, Vector3ic position) {
        var below = dimension.getBlockState(position);
        if (below.getBlockType() == BlockTypes.BUBBLE_COLUMN) {
            return below.getPropertyValue(BlockPropertyTypes.DRAG_DOWN);
        }
        if (below.getBlockType() == BlockTypes.MAGMA) {
            return true;
        }
        if (below.getBlockType() == BlockTypes.SOUL_SAND) {
            return false;
        }
        return null;
    }

    private static boolean isSourceWater(BlockState state) {
        return state.getBlockType().hasBlockTag(BlockTags.WATER) &&
               state.getBehavior() instanceof BlockLiquidBaseComponent &&
               BlockLiquidBaseComponent.isSource(state);
    }

    private static boolean setBubbleColumnDirection(
            Dimension dimension, Vector3ic position, BlockState current, boolean dragDown
    ) {
        var updated = current.setPropertyValue(BlockPropertyTypes.DRAG_DOWN, dragDown);
        return dimension.setBlockState(position, updated, 0, true, true, false);
    }

    private static boolean replaceWithBubbleColumn(
            Dimension dimension, Vector3ic position, BlockState water, boolean dragDown
    ) {
        var bubbleColumn = BlockTypes.BUBBLE_COLUMN.ofState(BlockPropertyTypes.DRAG_DOWN.createValue(dragDown));
        if (!dimension.setBlockState(position, water, 1, true, false, false)) {
            return false;
        }
        if (dimension.setBlockState(position, bubbleColumn, 0, true, true, false)) {
            return true;
        }

        dimension.setBlockState(position, BlockTypes.AIR.getDefaultState(), 1, true, false, false);
        return false;
    }

    private static boolean restoreWater(Dimension dimension, Vector3ic position) {
        var water = dimension.getBlockState(position, 1);
        if (!isSourceWater(water)) {
            water = BlockTypes.WATER.getDefaultState();
        }

        if (!dimension.setBlockState(position, water, 0, true, true, false)) {
            return false;
        }
        return dimension.setBlockState(position, BlockTypes.AIR.getDefaultState(), 1, true, false, false);
    }
}
