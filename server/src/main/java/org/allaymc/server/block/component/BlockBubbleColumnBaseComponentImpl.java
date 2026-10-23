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
 * {@code BlockBubbleColumnBaseComponentImpl} maintains upward chains of bubble
 * columns supported by magma, soul sand, or an existing column. The foreground bubble
 * block carries the drag direction, while layer one retains the source water that
 * occupied the same position. Column updates must keep those two representations
 * related when support changes or a layer write is refused.
 *
 * <p>
 * The update walk derives direction from the block below and proceeds upward until
 * water, height bounds, or a rejected write stops it. Removing support restores water
 * through the column. Conversion writes retained water before the bubble block and
 * attempts to clear the added layer if the foreground write fails; cleanup refusal
 * can leave that layer in place. Restoration writes foreground water before clearing
 * its retained layer. These operations suppress lifecycle callbacks to avoid
 * recursively reentering the same walk.
 *
 * @author Miroshka000
 */
public class BlockBubbleColumnBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates bubble column base behavior for the supplied block type.
     */
    public BlockBubbleColumnBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Forwards the inherited component event and refreshes this position's bubble
     * column chain. The refresh starts after placement has stored the incoming state
     * and may modify successive positions above it.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        updateAt(oldBlock.getDimension(), oldBlock.getPosition());
    }

    /**
     * Forwards the inherited component event and refreshes this position's bubble
     * column chain. The refresh starts after a neighboring block changes and may
     * modify successive positions above it.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        updateAt(block.getDimension(), block.getPosition());
    }

    /**
     * Forwards the inherited component event and refreshes this position's bubble
     * column chain. The refresh starts after another layer at the same position
     * changes and may modify successive positions above it.
     */
    @Override
    public void afterNeighborLayerReplace(Block currentBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterNeighborLayerReplace(currentBlock, newBlockState, placementInfo);
        updateAt(currentBlock.getDimension(), currentBlock.getPosition());
    }

    /**
     * Refreshes the bubble column chain starting immediately above the supplied
     * block. The block's dimension and position determine the starting point, and
     * updates may continue through successive water blocks.
     */
    public static void updateAbove(Block block) {
        updateAt(block.getDimension(), BlockFace.UP.offsetPos(block.getPosition()));
    }

    /**
     * Refreshes a bubble column chain from the supplied position upward within
     * dimension height bounds. The block below selects upward drag, downward drag, or
     * the absence of column support.
     *
     * <p>
     * Existing columns require retained source water in layer one; losing it restores
     * water and switches the remaining walk to support removal. Supported source
     * water can become a column, while unsupported columns return to water. A
     * rejected layer or state write ends the walk, leaving earlier accepted changes
     * in place.
     */
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

    /**
     * Returns the supporting block's drag direction, or {@code null} when it cannot
     * sustain a column. Magma selects downward drag, soul sand selects upward drag,
     * and an existing column supplies its stored direction.
     */
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

    /**
     * Returns whether the state is water with source-liquid behavior.
     */
    private static boolean isSourceWater(BlockState state) {
        return state.getBlockType().hasBlockTag(BlockTags.WATER) &&
               state.getBehavior() instanceof BlockLiquidBaseComponent &&
               BlockLiquidBaseComponent.isSource(state);
    }

    /**
     * Writes the updated drag direction without invoking block lifecycle callbacks.
     * The result reports dimension acceptance, while normal neighbor updates remain
     * enabled for the foreground state change.
     */
    private static boolean setBubbleColumnDirection(
            Dimension dimension, Vector3ic position, BlockState current, boolean dragDown
    ) {
        var updated = current.setPropertyValue(BlockPropertyTypes.DRAG_DOWN, dragDown);
        return dimension.setBlockState(position, updated, 0, true, true, false);
    }

    /**
     * Converts a source-water position into a foreground bubble column while
     * retaining water in layer one. The retained water write disables neighbor
     * updates, and both writes suppress block lifecycle callbacks.
     *
     * <p>
     * A rejected retained-water write leaves the foreground unchanged. If the bubble
     * write succeeds, the method returns {@code true}; otherwise it attempts to clear
     * the added layer and returns {@code false}. That cleanup result is ignored, so a
     * refused cleanup can leave retained water behind.
     */
    private static boolean replaceWithBubbleColumn(
            Dimension dimension, Vector3ic position, BlockState water, boolean dragDown
    ) {
        var bubbleColumn = BlockTypes.BUBBLE_COLUMN.ofState(BlockPropertyTypes.DRAG_DOWN.createValue(dragDown));
        // Retain source water before publishing the bubble foreground so
        // liquid-dependent callers can still find water at the column position.
        if (!dimension.setBlockState(position, water, 1, true, false, false)) {
            return false;
        }
        if (dimension.setBlockState(position, bubbleColumn, 0, true, true, false)) {
            return true;
        }

        // The bubble foreground was refused after retained water was accepted.
        // Attempt to remove that extra layer before reporting conversion failure.
        dimension.setBlockState(position, BlockTypes.AIR.getDefaultState(), 1, true, false, false);
        return false;
    }

    /**
     * Restores foreground source water and then clears the retained water layer.
     * Valid source water from layer one is reused; missing or incompatible retained
     * water selects the default water state.
     *
     * <p>
     * The foreground write enables neighbor updates but suppresses block lifecycle
     * callbacks. Failure stops before layer cleanup, preserving the existing retained
     * water. Successful foreground replacement is followed by layer-one clearing, and
     * the returned result reflects that final write; failure does not undo the
     * foreground change.
     */
    private static boolean restoreWater(Dimension dimension, Vector3ic position) {
        var water = dimension.getBlockState(position, 1);
        if (!isSourceWater(water)) {
            // Invalid retained water cannot restore a source column. Use default
            // water so support removal still produces the source-water foreground.
            water = BlockTypes.WATER.getDefaultState();
        }

        // Restore foreground water before clearing its retained layer, so a rejected
        // foreground write leaves the existing liquid representation available.
        if (!dimension.setBlockState(position, water, 0, true, true, false)) {
            return false;
        }
        return dimension.setBlockState(position, BlockTypes.AIR.getDefaultState(), 1, true, false, false);
    }
}
