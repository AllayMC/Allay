package org.allaymc.server.world.feature.tree;

import lombok.Getter;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.allaymc.server.world.feature.AbstractWorldFeature;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Abstract base class for tree world features.
 * Provides common methods for tree generation.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class TreeWorldFeature extends AbstractWorldFeature {

    protected final BlockType<?> logType;
    protected final BlockType<?> leavesType;
    protected final BlockType<?> saplingType;
    protected final int minHeight;
    protected final int maxHeight;

    public TreeWorldFeature(
            Identifier identifier,
            BlockType<?> logType,
            BlockType<?> leavesType,
            BlockType<?> saplingType,
            int minHeight,
            int maxHeight
    ) {
        super(identifier);
        this.logType = logType;
        this.leavesType = leavesType;
        this.saplingType = saplingType;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    /**
     * Calculate the actual height for this tree placement.
     *
     * @return the calculated height
     */
    protected int calculateHeight() {
        return getMinHeight() + ThreadLocalRandom.current().nextInt(getMaxHeight() - getMinHeight() + 1);
    }

    /**
     * Check if a block can be replaced by tree generation.
     *
     * @param blockState the block state to check
     * @return true if the block can be replaced
     */
    protected boolean canReplace(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockType == BlockTypes.AIR ||
               blockType.hasBlockTag(BlockTags.REPLACEABLE) ||
               blockType.hasBlockTag(BlockTags.LEAVES);
    }

    /**
     * Check if a block can support tree growth from below.
     *
     * @param blockState the block state to check
     * @return true if the block can support the tree
     */
    protected boolean canGrowOn(BlockState blockState) {
        return blockState.getBlockType().hasBlockTag(BlockTags.DIRT);
    }

    /**
     * Check if a log can be placed at the specified position.
     *
     * @param context the feature context
     * @param x       the x coordinate
     * @param y       the y coordinate
     * @param z       the z coordinate
     * @return true if a log can be placed
     */
    protected boolean canPlaceLog(WorldFeatureContext context, int x, int y, int z) {
        return canReplace(context.getBlockState(x, y, z));
    }

    /**
     * Check if leaves can be placed at the specified position.
     *
     * @param context the feature context
     * @param x       the x coordinate
     * @param y       the y coordinate
     * @param z       the z coordinate
     * @return true if leaves can be placed
     */
    protected boolean canPlaceLeaves(WorldFeatureContext context, int x, int y, int z) {
        var existing = context.getBlockState(x, y, z);
        return canReplace(existing);
    }

    /**
     * Place a log block at the specified position.
     *
     * @param context the feature context
     * @param x       the x coordinate
     * @param y       the y coordinate
     * @param z       the z coordinate
     */
    protected void placeLog(WorldFeatureContext context, int x, int y, int z) {
        context.setBlockState(x, y, z, logType.getDefaultState());
    }

    /**
     * Place a leaves block at the specified position.
     *
     * @param context the feature context
     * @param x       the x coordinate
     * @param y       the y coordinate
     * @param z       the z coordinate
     */
    protected void placeLeaves(WorldFeatureContext context, int x, int y, int z) {
        if (canPlaceLeaves(context, x, y, z)) {
            context.setBlockState(x, y, z, leavesType.getDefaultState());
        }
    }

    /**
     * Check if the tree can generate at the specified position.
     *
     * @param context the feature context
     * @param x       the x coordinate
     * @param y       the y coordinate
     * @param z       the z coordinate
     * @param height  the proposed tree height
     * @return true if the tree can generate
     */
    protected boolean canGenerate(WorldFeatureContext context, int x, int y, int z, int height) {
        // Check ground
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        // Check trunk space
        for (int dy = 0; dy < height; dy++) {
            if (!canPlaceLog(context, x, y + dy, z)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Place dirt block under the tree.
     *
     * @param context the feature context
     * @param x       the x coordinate
     * @param y       the y coordinate (below trunk)
     * @param z       the z coordinate
     */
    protected void placeDirtUnder(WorldFeatureContext context, int x, int y, int z) {
        var blockBelow = context.getBlockState(x, y, z);
        if (blockBelow.getBlockType() == BlockTypes.GRASS_BLOCK ||
            blockBelow.getBlockType() == BlockTypes.FARMLAND) {
            context.setBlockState(x, y, z, BlockTypes.DIRT.getDefaultState());
        }
    }
}
