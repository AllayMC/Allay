package org.allaymc.server.block.component;

import it.unimi.dsi.fastutil.ints.IntObjectImmutablePair;
import it.unimi.dsi.fastutil.ints.IntObjectPair;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.component.data.BlockStateData;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.List;

import static org.allaymc.api.block.component.BlockLiquidBaseComponent.*;

/**
 * @author daoge_cmd
 */
public abstract class BlockLiquidBaseComponentImpl extends BlockBaseComponentImpl implements BlockLiquidBaseComponent {

    public BlockLiquidBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean canResetFallDamage() {
        return true;
    }

    @Override
    public boolean canCollideWithEntity() {
        return true;
    }

    @Override
    public void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.onPlace(currentBlockState, newBlockState, placementInfo);
        tryScheduleLiquidUpdate(currentBlockState);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);
        tryScheduleLiquidUpdate(current);
    }

    private void tryScheduleLiquidUpdate(BlockStateWithPos current) {
        var blockUpdateService = current.dimension().getBlockUpdateService();
        if (!blockUpdateService.hasScheduledBlockUpdate(current.pos(), current.layer())) {
            blockUpdateService.scheduleBlockUpdate(current.pos(), getFlowSpeed(current.dimension().getDimensionInfo()), current.layer());
        }
    }

    @Override
    public void onScheduledUpdate(BlockStateWithPos blockStateWithPos) {
        var pos = blockStateWithPos.pos();
        var dimension = blockStateWithPos.dimension();
        var liquid = blockStateWithPos.blockState();
        if (canFormSource() && getDepth(liquid) == 7) {
            // Attempt to form new water source blocks.
            var count = 0;
            for (var face : BlockFace.getHorizontalBlockFaces()) {
                var neighbor = dimension.getBlockState(face.offsetPos(pos));
                if (isSameLiquidType(neighbor.getBlockType()) && isSource(neighbor)) {
                    count++;
                }
            }
            if (count >= 2) {
                if (!canFlowInto(dimension, liquid, BlockFace.DOWN.offsetPos(pos), true)) {
                    // Only form a new source block if there either is no water below this block,
                    // or if the water below this is not falling (full source block).
                    var newLiquid = getSourceBlockState();
                    // TODO: liquid flow event
                    setLiquidInWorld(dimension, pos, newLiquid);
                }
            }
        }
        updateLiquid(dimension, pos, liquid, blockStateWithPos.layer() == 1);
    }

    /**
     * Update the liquid block passed at a specific position in the world. Depending on the surroundings
     * and the liquid block, the liquid will either spread or decrease in depth.
     *
     * @param dimension   The dimension the block is in.
     * @param pos         The position of the liquid block.
     * @param liquid      The block state of the liquid.
     * @param isContained Whether the liquid is contained in a block.
     */
    protected void updateLiquid(Dimension dimension, Vector3ic pos, BlockState liquid, boolean isContained) {
        if (!isSource(liquid) && !hasSupplyLiquidAround(dimension, pos, liquid)) {
            BlockState newLiquid = null;
            if (getDepth(liquid) - 4 > 0) {
                newLiquid = getLiquidBlockState(getDepth(liquid) - 2 * getFlowDecay(dimension.getDimensionInfo()), false);
            }
            // TODO: liquid decay event
            setLiquidInWorld(dimension, pos, newLiquid);
            return;
        }

        var liquidContainer = isContained ? dimension.getBlockState(pos) : null;
        var canFlowBelow = canFlowInto(dimension, liquid, BlockFace.DOWN.offsetPos(pos), false);
        if (isFalling(liquid) && !canFlowBelow) {
            liquid = getFallingBlockState();
        } else if (canFlowBelow) {
            var below = BlockFace.DOWN.offsetPos(pos);
            if (liquidContainer == null || !liquidContainer.getBehavior().canLiquidFlowIntoSide(liquidContainer, BlockFace.DOWN)) {
                flowInto(dimension, pos, getFallingBlockState(), below, true);
            }
        }

        var depth = getDepth(liquid);
        var decay = getFlowDecay(dimension.getDimensionInfo());
        if (depth <= decay) {
            return;
        }
        if (isSource(liquid) || !canFlowBelow) {
            var paths = calculateLiquidPaths(dimension, pos, liquid, liquidContainer);
            if (paths.isEmpty()) {
                spreadOutwards(dimension, pos, liquid, liquidContainer);
                return;
            }

            var smallestLength = paths.getFirst().length;
            for (var path : paths) {
                if (path.length <= smallestLength) {
                    flowInto(dimension, pos, liquid, path[0], false);
                }
            }
        }
    }

    /**
     * Spread the liquid outwards into the horizontal directions.
     *
     * @param dimension       The dimension the block is in.
     * @param src             The source position of the liquid.
     * @param liquid          The block state of the liquid.
     * @param liquidContainer The block state that contains this liquid block, can be {@code null} if the liquid is not contained.
     */
    protected void spreadOutwards(Dimension dimension, Vector3ic src, BlockState liquid, BlockState liquidContainer) {
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            if (liquidContainer == null || !liquidContainer.getBehavior().canLiquidFlowIntoSide(liquidContainer, face)) {
                flowInto(dimension, src, liquid, face.offsetPos(src), false);
            }
        }
    }

    /**
     * Check if there is a supply liquid in the blocks around the position passed.
     *
     * @param dimension The dimension the block is in.
     * @param pos       The position to check around.
     *
     * @return Whether there is a supply liquid around the position.
     */
    protected boolean hasSupplyLiquidAround(Dimension dimension, Vector3ic pos, BlockState liquid) {
        for (var face : BlockFace.values()) {
            if (face == BlockFace.DOWN) {
                // We don't care about water below this one.
                continue;
            }

            var neighborPos = face.offsetPos(pos);
            var neighbor = getLiquidInWorld(dimension, neighborPos);
            var neighborLiquidLayer = neighbor.leftInt();
            var neighborLiquid = neighbor.right();
            if (neighborLiquid == null || !isSameLiquidType(neighborLiquid.getBlockType())) {
                continue;
            }

            if (neighborLiquidLayer == 1) {
                // The liquid is contained in a block, so we need to check if it can flow into this block.
                var layer0 = dimension.getBlockState(neighborPos);
                if (layer0.getBlockStateData().canContainLiquid() && !layer0.getBehavior().canLiquidFlowIntoSide(layer0, face)) {
                    // The side towards this liquid was closed, so this
                    // cannot function as a source for this liquid.
                    continue;
                }
            }

            if (face == BlockFace.UP ||
                isSource(neighborLiquid) ||
                getDepth(neighborLiquid) > getDepth(liquid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Make the liquid passed flow into the position passed in a world. If successful, the block at that
     * position will be broken and the liquid with a lower depth will replace it.
     *
     * @param dimension The dimension the block is in.
     * @param src       The position the liquid is flowing from.
     * @param liquid    The block state of the liquid.
     * @param pos       The position to flow into.
     * @param falling   Whether the liquid is falling or not.
     *
     * @return Whether the liquid successfully flowed into the position.
     */
    protected boolean flowInto(Dimension dimension, Vector3ic src, BlockState liquid, Vector3ic pos, boolean falling) {
        var newDepth = getDepth(liquid);
        if (!falling) {
            newDepth -= getFlowDecay(dimension.getDimensionInfo());
        }
        if (newDepth <= 0 && !falling) {
            return false;
        }
        var existing = dimension.getBlockState(pos);
        if (isSameLiquidType(existing.getBlockType())) {
            if (getDepth(existing) >= newDepth || isFalling(existing)) {
                return true;
            }
            // TODO: liquid flow event
            setLiquidInWorld(dimension, pos, getLiquidBlockState(newDepth, falling));
            return true;
        }

        var canContainLiquid = existing.getBlockStateData().canContainLiquid();
        if (canContainLiquid) {
            if (getLiquidInWorld(dimension, pos).right() != null) {
                // We've got a liquid displacer, and it's got a liquid within it, so we can't flow into this.
                return false;
            }
        }

        var liquidReactionOnTouch = existing.getBlockStateData().liquidReactionOnTouch();
        var removedOnTouch = liquidReactionOnTouch.removedOnTouch();
        if (!removedOnTouch && (!canContainLiquid || !canContainSpecificLiquid(existing.getBlockStateData(), getLiquidBlockState(newDepth, falling)))) {
            // Can't flow into this block.
            return false;
        }
        // TODO: liquid flow event
        if (removedOnTouch) {
            switch (liquidReactionOnTouch) {
                case BROKEN -> dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
                case POPPED -> dimension.breakBlock(pos, null, null);
            }
        }
        setLiquidInWorld(dimension, pos, getLiquidBlockState(newDepth, falling));
        return true;
    }

    /**
     * Calculate paths in the world that the liquid passed can
     * flow in to reach lower grounds, starting at the position passed.
     * If none of these paths can be found, the returned list has a length of 0.
     *
     * @param dimension       The dimension the block is in.
     * @param src             The position to start the flow from.
     * @param liquid          The block state of the liquid.
     * @param liquidContainer The block state that contains this liquid block, can be {@code null} if the liquid is not contained.
     *
     * @return A list of paths that the liquid can flow in.
     */
    protected List<Vector3ic[]> calculateLiquidPaths(Dimension dimension, Vector3ic src, BlockState liquid, BlockState liquidContainer) {
        var queue = new LiquidQueue();
        queue.pushBack(new LiquidNode(src.x(), src.z(), (byte) getDepth(liquid), null));
        var decay = getFlowDecay(dimension.getDimensionInfo());

        var paths = new ArrayList<Vector3ic[]>();
        var first = true;

        while (queue.length() != 0) {
            var node = queue.front();
            var neighbors = node.neighbors(decay * 2);
            var neighborA = neighbors[0]; // WEST
            var neighborB = neighbors[1]; // EAST
            var neighborC = neighbors[2]; // NORTH
            var neighborD = neighbors[3]; // SOUTH

            if (!first || (liquidContainer == null || !liquidContainer.getBehavior().canLiquidFlowIntoSide(liquidContainer, BlockFace.WEST))) {
                if (spreadNeighbor(dimension, liquid, src, neighborA, queue)) {
                    queue.shortestPath = neighborA.length();
                    paths.add(neighborA.path(src));
                }
            }
            if (!first || (liquidContainer == null || !liquidContainer.getBehavior().canLiquidFlowIntoSide(liquidContainer, BlockFace.EAST))) {
                if (spreadNeighbor(dimension, liquid, src, neighborB, queue)) {
                    queue.shortestPath = neighborB.length();
                    paths.add(neighborB.path(src));
                }
            }
            if (!first || (liquidContainer == null || !liquidContainer.getBehavior().canLiquidFlowIntoSide(liquidContainer, BlockFace.NORTH))) {
                if (spreadNeighbor(dimension, liquid, src, neighborC, queue)) {
                    queue.shortestPath = neighborC.length();
                    paths.add(neighborC.path(src));
                }
            }
            if (!first || (liquidContainer == null || !liquidContainer.getBehavior().canLiquidFlowIntoSide(liquidContainer, BlockFace.SOUTH))) {
                if (spreadNeighbor(dimension, liquid, src, neighborD, queue)) {
                    queue.shortestPath = neighborD.length();
                    paths.add(neighborD.path(src));
                }
            }
            first = false;
        }
        return paths;
    }

    /**
     * spreadNeighbour attempts to spread a path node into the neighbour passed. Note that this
     * does not spread the liquid, it only spreads the node used to calculate flow paths.
     *
     * @param dimension The dimension the block is in.
     * @param liquid    The block state of the liquid.
     * @param source    The source position of the liquid.
     * @param node      The node to spread.
     * @param queue     The queue to push the node into if it can spread.
     *
     * @return Whether the node could spread into the neighbour.
     */
    protected boolean spreadNeighbor(Dimension dimension, BlockState liquid, Vector3ic source, LiquidNode node, LiquidQueue queue) {
        if (
            // Depth has reached zero or below, can't spread any further.
                node.depth() + 3 <= 0 ||
                // This path is longer than any existing path, so don't spread any further.
                node.length() > queue.shortestPath
        ) {
            return false;
        }

        var pos = new Vector3i(node.x(), source.y(), node.z());
        if (!canFlowInto(dimension, liquid, pos, true)) {
            // Can't flow into this block, can't spread any further.
            return false;
        }

        // Try flow down
        pos.y--;
        if (canFlowInto(dimension, liquid, pos, false)) {
            return true;
        }

        queue.pushBack(node);
        return false;
    }

    /**
     * Checks if a liquid can flow into the block present in the world at a specific block position.
     *
     * @param dimension The dimension the block is in.
     * @param liquid    The block state of the liquid.
     * @param pos       The position of the block to flow into.
     * @param sideways  Whether the flow is sideways or downwards.
     *
     * @return Whether the liquid can flow into the block.
     */
    protected boolean canFlowInto(Dimension dimension, BlockState liquid, Vector3ic pos, boolean sideways) {
        var existing = dimension.getBlockState(pos);
        if (existing.getBlockType() == BlockTypes.AIR ||
            existing.getBlockStateData().liquidReactionOnTouch().removedOnTouch()) {
            return true;
        }

        if (existing.getBehavior() instanceof BlockLiquidBaseComponent) {
            if (!sideways) {
                return true;
            }
            // Can't flow into a source liquid or a different type of liquid.
            return !isSource(existing) && isSameLiquidType(existing.getBlockType());
        }

        if (existing.getBlockStateData().canContainLiquid()) {
            var newLiquid = getLiquidBlockState(getDepth(liquid) - getFlowDecay(dimension.getDimensionInfo()), !sideways);
            return canContainSpecificLiquid(existing.getBlockStateData(), newLiquid);
        }

        return false;
    }

    protected static final IntObjectPair<BlockState> PAIR_LIQUID_NOT_FOUND = new IntObjectImmutablePair<>(-1, null);

    /**
     * Attempts to return a liquid block at the position passed. This
     * liquid may be in the foreground or in any other layer. If found,
     * the liquid is returned. If not, the boolean returned is false.
     *
     * @param dimension The dimension the block is in.
     * @param pos       The position to check for a liquid block.
     *
     * @return The liquid block at the position and the layer it is in, or (-1, null) if no liquid was found.
     */
    protected static IntObjectPair<BlockState> getLiquidInWorld(Dimension dimension, Vector3ic pos) {
        var layer0 = dimension.getBlockState(pos);
        if (layer0.getBehavior() instanceof BlockLiquidBaseComponent) {
            return new IntObjectImmutablePair<>(0, layer0);
        }

        if (!layer0.getBlockStateData().canContainLiquid()) {
            return PAIR_LIQUID_NOT_FOUND;
        }

        var layer1 = dimension.getBlockState(pos, 1);
        if (layer1.getBehavior() instanceof BlockLiquidBaseComponent) {
            return new IntObjectImmutablePair<>(1, layer1);
        }

        return PAIR_LIQUID_NOT_FOUND;
    }

    /**
     * Set a liquid at a specific position in the dimension. Unlike {@link Dimension#setBlockState},
     * this method will not necessarily overwrite any existing blocks. It
     * will instead be in the same position as a block currently there, unless
     * there already is a liquid at that position, in which case it will be
     * overwritten. If null is passed for the liquid, any liquid currently present
     * will be removed.
     *
     * @param dimension The dimension the block is in.
     * @param pos       The position to set the Liquid at.
     * @param liquid    The liquid to set at the position, or {@code null} to remove any Liquid.
     */
    protected static void setLiquidInWorld(Dimension dimension, Vector3ic pos, BlockState liquid) {
        if (!dimension.isInWorld(pos.x(), pos.y(), pos.z())) {
            return;
        }

        if (liquid == null) {
            removeLiquidInWorld(dimension, pos);
            return;
        }

        var existingBlockState = dimension.getBlockState(pos);
        if (!existingBlockState.getBlockType().hasBlockTag(BlockCustomTags.REPLACEABLE)) {
            if (!canContainSpecificLiquid(existingBlockState.getBlockStateData(), liquid)) {
                return;
            }
        }

        if (removeLiquidInWorld(dimension, pos)) {
            dimension.setBlockState(pos, liquid, 0);
        } else {
            dimension.setBlockState(pos, liquid, 1);
        }
    }

    /**
     * Remove any liquid blocks that may be present at a specific
     * block position. The bool returned specifies if no blocks
     * were left on layer 0.
     *
     * @param dimension The dimension the block is in.
     * @param pos       The position to remove the liquid from.
     *
     * @return If no blocks were left on layer 0.
     */
    protected static boolean removeLiquidInWorld(Dimension dimension, Vector3ic pos) {
        var layer0 = dimension.getBlockState(pos);
        if (layer0.getBlockType() == BlockTypes.AIR) {
            return true;
        }

        if (layer0.getBehavior() instanceof BlockLiquidBaseComponent) {
            dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
            return true;
        }

        if (layer0.getBlockStateData().canContainLiquid()) {
            var layer1 = dimension.getBlockState(pos, 1);
            if (layer1.getBehavior() instanceof BlockLiquidBaseComponent) {
                dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState(), 1);
                return false;
            }
        }

        return false;
    }

    /**
     * Check if the block with specific block state data
     * can contain the specific liquid block state.
     *
     * @param blockStateData   The block state data of the block.
     * @param liquidBlockState The liquid block state to check if it can be contained.
     *
     * @return Whether the block can contain the liquid.
     */
    protected static boolean canContainSpecificLiquid(BlockStateData blockStateData, BlockState liquidBlockState) {
        return isSource(liquidBlockState) ? blockStateData.canContainLiquidSource() : blockStateData.canContainLiquid();
    }

    /**
     * liquidNode represents a position that is part of a flow path for a liquid.
     *
     * @param x        The x coordinate of the node.
     * @param z        The z coordinate of the node.
     * @param depth    The depth of the node. Byte is used to save memory.
     * @param previous The previous node in the path.
     */
    protected record LiquidNode(int x, int z, byte depth, LiquidNode previous) {
        /**
         * neighbours returns the four horizontal neighbours of the node with decreased depth.
         *
         * @param decay The amount to decrease the depth by.
         *
         * @return The neighbours of the node.
         */
        public LiquidNode[] neighbors(int decay) {
            return new LiquidNode[]{
                    // WEST
                    new LiquidNode(x - 1, z, (byte) (depth - decay), this),
                    // EAST
                    new LiquidNode(x + 1, z, (byte) (depth - decay), this),
                    // NORTH
                    new LiquidNode(x, z - 1, (byte) (depth - decay), this),
                    // SOUTH
                    new LiquidNode(x, z + 1, (byte) (depth - decay), this)
            };
        }

        /**
         * Get the length of the path created by the node.
         *
         * @return The length of the path.
         */
        public int length() {
            int i = 1;
            LiquidNode node = this;
            while (true) {
                if (node.previous == null) {
                    return i - 1;
                }
                node = node.previous;
                i++;
            }
        }

        /**
         * Convert the liquid node into a path.
         *
         * @return The path created by the node.
         */
        public Vector3ic[] path(Vector3ic sourcePos) {
            var node = this;
            int length = node.length();
            var path = new Vector3ic[length];
            int i = length - 1;
            while (true) {
                if (node.previous == null) {
                    return path;
                }

                path[i] = new Vector3i(node.x, sourcePos.y(), node.z);
                node = node.previous;
                i--;
            }
        }
    }

    /**
     * LiquidQueue represents a queue that may be used to push nodes into and take them out of it.
     */
    protected static final class LiquidQueue {

        private final ArrayList<LiquidNode> nodes;
        private int shortestPath;

        public LiquidQueue() {
            nodes = new ArrayList<>();
            shortestPath = Integer.MAX_VALUE;
        }

        public void pushBack(LiquidNode node) {
            nodes.add(node);
        }

        public LiquidNode front() {
            return nodes.removeFirst();
        }

        public int length() {
            return nodes.size();
        }
    }
}
