package org.allaymc.server.block.component;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.LiquidDecayEvent;
import org.allaymc.api.eventbus.event.block.LiquidFlowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.world.physics.AllayEntityPhysicsEngine;
import org.joml.Vector3d;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.allaymc.api.block.component.BlockLiquidBaseComponent.*;

/**
 * @author daoge_cmd
 */
@Slf4j
public abstract class BlockLiquidBaseComponentImpl extends BlockBaseComponentImpl implements BlockLiquidBaseComponent {
    public BlockLiquidBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onScheduledUpdate(Block block) {
        var pos = block.getPosition();
        var dimension = block.getDimension();
        if (canFormSource() && getDepth(block.getBlockState()) == 7) {
            // Attempt to form new water source blocks.
            var count = 0;
            for (var face : BlockFace.getHorizontalBlockFaces()) {
                var neighbor = block.offsetPos(face);
                if (isSameLiquidType(neighbor.getBlockType()) && isSource(neighbor.getBlockState())) {
                    count++;
                }
            }

            if (count >= 2) {
                if (!canFlowInto(dimension, BlockFace.DOWN.offsetPos(pos), true)) {
                    // Only form a new source block if there either is no water below this block,
                    // or if the water below this is not falling (full source block).
                    var newLiquid = getSourceBlockState();
                    var event = new LiquidFlowEvent(block, pos, newLiquid, true);
                    if (!event.call()) {
                        return;
                    }
                    dimension.setLiquid(pos, newLiquid);
                }
            }
        }

        updateLiquid(dimension, pos, block.getBlockState(), block.getLayer());
    }

    @Override
    public void onPlace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.onPlace(block, newBlockState, placementInfo);
        tryScheduleLiquidUpdate(block);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);
        tryScheduleLiquidUpdate(block);
    }

    protected void tryScheduleLiquidUpdate(Block current) {
        var blockUpdateManager = current.getDimension().getBlockUpdateManager();
        if (!blockUpdateManager.hasScheduledBlockUpdate(current.getPosition())) {
            blockUpdateManager.scheduleBlockUpdateInDelay(current.getPosition(), getFlowSpeed(current.getDimension().getDimensionInfo()));
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Collections.emptySet();
    }

    @Override
    public boolean canResetFallDamage() {
        return true;
    }

    /**
     * This method is used in {@link AllayEntityPhysicsEngine}
     */
    public Vector3d calculateFlowVector(Dimension dimension, int x, int y, int z, BlockState current) {
        // TODO: cache the flow vector for better performance
        var vx = 0;
        var vy = 0;
        var vz = 0;
        var decay = getEffectiveFlowDecay(current);

        for (var face : BlockFace.getHorizontalBlockFaces()) {
            var offset = face.getOffset();

            var sideX = x + offset.x();
            var sideY = y + offset.y();
            var sideZ = z + offset.z();
            var sideBlock = dimension.getBlockState(sideX, sideY, sideZ);
            var blockDecay = getEffectiveFlowDecay(sideBlock);

            if (blockDecay < 0) {
                if (!sideBlock.getBlockStateData().liquidReactionOnTouch().canLiquidFlowInto()) {
                    continue;
                }

                blockDecay = getEffectiveFlowDecay(dimension.getBlockState(sideX, sideY - 1, sideZ));

                if (blockDecay >= 0) {
                    var realDecay = blockDecay - (decay - 8);
                    vx += offset.x() * realDecay;
                    vy += offset.y() * realDecay;
                    vz += offset.z() * realDecay;
                }

                continue;
            }

            var realDecay = blockDecay - decay;
            vx += offset.x() * realDecay;
            vy += offset.y() * realDecay;
            vz += offset.z() * realDecay;
        }

        var vector = new Vector3d(vx, vy, vz);

        if (isFalling(current)) {
            for (var face : BlockFace.getHorizontalBlockFaces()) {
                var offset = face.getOffset();
                if (!canFlowInto(dimension, x + offset.x(), y + offset.y(), z + offset.z(), true) &&
                    !canFlowInto(dimension, x + offset.x(), y + offset.y() + 1, z + offset.z(), true)) {
                    // normalize() should only be called when the vector is not zero,
                    // otherwise it will produce a vector with three NaN values.
                    MathUtils.normalizeIfNotZero(vector);
                    vector.add(0, -6, 0);
                    break;
                }
            }
        }

        // Same to above
        return MathUtils.normalizeIfNotZero(vector);
    }

    protected int getEffectiveFlowDecay(BlockState liquid) {
        if (!isSameLiquidType(liquid.getBlockType())) {
            return -1;
        }

        return isFalling(liquid) ? 0 : 8 - getDepth(liquid);
    }

    /**
     * Update the liquid block passed at a specific position in the world. Depending on the surroundings
     * and the liquid block, the liquid will either spread or decrease in depth.
     *
     * @param dimension The dimension the block is in
     * @param pos       The position of the liquid block
     * @param liquid    The block state of the liquid
     * @param layer     The layer the liquid is in
     */
    protected void updateLiquid(Dimension dimension, Vector3ic pos, BlockState liquid, int layer) {
        if (!isSource(liquid) && !hasSupplyLiquidAround(dimension, pos, liquid)) {
            BlockState newLiquid = null;
            if (getDepth(liquid) - 4 > 0) {
                newLiquid = getLiquidBlockState(getDepth(liquid) - 2 * getFlowDecay(dimension.getDimensionInfo()), false);
            }
            var event = new LiquidDecayEvent(new Block(liquid, new Position3i(pos, dimension), layer), newLiquid);
            if (!event.call()) {
                return;
            }
            dimension.setLiquid(pos, newLiquid);
            return;
        }

        var liquidContainer = layer == 1 ? dimension.getBlockState(pos) : null;
        var canFlowBelow = canFlowInto(dimension, BlockFace.DOWN.offsetPos(pos), false);
        if (isFalling(liquid) && !canFlowBelow) {
            liquid = getFallingBlockState();
        } else if (canFlowBelow) {
            var below = BlockFace.DOWN.offsetPos(pos);
            if (liquidContainer == null || liquidContainer.getBehavior().canLiquidFlowIntoSide(liquidContainer, BlockFace.DOWN)) {
                flowInto(dimension, pos, layer, getFallingBlockState(), below, true);
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
                    flowInto(dimension, pos, layer, liquid, path[0], false);
                }
            }
        }
    }

    /**
     * Spread the liquid outwards into the horizontal directions.
     *
     * @param dimension       The dimension the block is in
     * @param src             The source position of the liquid
     * @param liquid          The block state of the liquid
     * @param liquidContainer The block state that contains this liquid block, can be {@code null} if the liquid is not contained
     */
    protected void spreadOutwards(Dimension dimension, Vector3ic src, BlockState liquid, BlockState liquidContainer) {
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            if (liquidContainer == null || liquidContainer.getBehavior().canLiquidFlowIntoSide(liquidContainer, face)) {
                flowInto(dimension, src, liquidContainer == null ? 0 : 1, liquid, face.offsetPos(src), false);
            }
        }
    }

    /**
     * Check if there is a supply liquid in the blocks around the position passed.
     *
     * @param dimension The dimension the block is in
     * @param pos       The position to check around
     * @return Whether there is a supply liquid around the position
     */
    protected boolean hasSupplyLiquidAround(Dimension dimension, Vector3ic pos, BlockState liquid) {
        for (var face : BlockFace.VALUES) {
            if (face == BlockFace.DOWN) {
                // We don't care about water below this one.
                continue;
            }

            var neighborPos = face.offsetPos(pos);
            var neighbor = dimension.getLiquid(neighborPos);
            var neighborLiquidLayer = neighbor.left();
            var neighborLiquid = neighbor.right();
            if (neighborLiquid == null || !isSameLiquidType(neighborLiquid.getBlockType())) {
                continue;
            }

            if (neighborLiquidLayer == 1) {
                // The liquid is contained in a block, so we need to check if it can flow into this block.
                var layer0 = dimension.getBlockState(neighborPos);
                if (!layer0.getBehavior().canLiquidFlowIntoSide(layer0, face)) {
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
     * @param dimension The dimension the block is in
     * @param src       The position the liquid is flowing from
     * @param srcLayer  The layer the liquid is flowing from
     * @param liquid    The block state of the liquid
     * @param pos       The position to flow into
     * @param falling   Whether the liquid is falling or not
     * @return Whether the liquid successfully flowed into the position
     */
    protected boolean flowInto(Dimension dimension, Vector3ic src, int srcLayer, BlockState liquid, Vector3ic pos, boolean falling) {
        var newDepth = getDepth(liquid);
        if (!falling) {
            newDepth -= getFlowDecay(dimension.getDimensionInfo());
        }
        if (newDepth <= 0 && !falling) {
            return false;
        }
        var existing = dimension.getBlockState(pos);
        if (existing.getBehavior() instanceof BlockLiquidBaseComponent existingLiquidBaseComponent) {
            if (isSameLiquidType(existing.getBlockType())) {
                if (getDepth(existing) >= newDepth || isFalling(existing)) {
                    return true;
                }
                var event = new LiquidFlowEvent(
                        new Block(liquid, new Position3i(src, dimension), srcLayer),
                        pos, existing
                );
                if (!event.call()) {
                    return false;
                }

                dimension.setLiquid(pos, getLiquidBlockState(newDepth, falling));
                return true;
            }

            // Not the same liquid type, try to harden the existing liquid.
            existingLiquidBaseComponent.tryHarden(
                    new Block(existing, new Position3i(pos, dimension), 0),
                    new Block(liquid, new Position3i(src, dimension), 0)
            );
            return false;
        }

        var liquidReactionOnTouch = existing.getBlockStateData().liquidReactionOnTouch();
        var canContainLiquid =
                // We can't flow into if the liquid cannot be contained (e.g. lava)
                canBeContained() &&
                // We can't flow into if the existing block can't contain liquid
                liquidReactionOnTouch.canLiquidFlowInto();
        if (canContainLiquid) {
            if (dimension.getLiquid(pos).right() != null) {
                // We've got a liquid displacer, and it's got a liquid within it, so we can't flow into this.
                return false;
            }
        }

        var removedOnTouch = liquidReactionOnTouch.shouldRemoveOnTouch();
        if (!(existing.getBlockType() == BlockTypes.AIR || removedOnTouch) && (!canContainLiquid || !liquidReactionOnTouch.canLiquidFlowInto()/*canContainSpecificLiquid(existing.getBlockStateData(), getLiquidBlockState(newDepth, falling))*/)) {
            // Can't flow into this block.
            return false;
        }

        var event = new LiquidFlowEvent(
                new Block(getLiquidBlockState(newDepth, falling), new Position3i(src, dimension), srcLayer),
                pos, existing
        );
        if (!event.call()) {
            return false;
        }

        if (removedOnTouch) {
            switch (liquidReactionOnTouch) {
                case BROKEN -> dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
                case POPPED -> dimension.breakBlock(pos, null, null, false);
            }
        }
        dimension.setLiquid(pos, getLiquidBlockState(newDepth, falling));
        return true;
    }

    /**
     * Calculate paths in the world that the liquid passed can
     * flow in to reach lower grounds, starting at the position passed.
     * If none of these paths can be found, the returned list has a length of 0.
     *
     * @param dimension       The dimension the block is in
     * @param src             The position to start the flow from
     * @param liquid          The block state of the liquid
     * @param liquidContainer The block state that contains this liquid block, can be {@code null} if the liquid is not contained
     * @return A list of paths that the liquid can flow in
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

            if (!first || (liquidContainer == null || liquidContainer.getBehavior().canLiquidFlowIntoSide(liquidContainer, BlockFace.WEST))) {
                if (spreadNeighbor(dimension, src, neighborA, queue)) {
                    queue.shortestPath = neighborA.length();
                    paths.add(neighborA.path(src));
                }
            }
            if (!first || (liquidContainer == null || liquidContainer.getBehavior().canLiquidFlowIntoSide(liquidContainer, BlockFace.EAST))) {
                if (spreadNeighbor(dimension, src, neighborB, queue)) {
                    queue.shortestPath = neighborB.length();
                    paths.add(neighborB.path(src));
                }
            }
            if (!first || (liquidContainer == null || liquidContainer.getBehavior().canLiquidFlowIntoSide(liquidContainer, BlockFace.NORTH))) {
                if (spreadNeighbor(dimension, src, neighborC, queue)) {
                    queue.shortestPath = neighborC.length();
                    paths.add(neighborC.path(src));
                }
            }
            if (!first || (liquidContainer == null || liquidContainer.getBehavior().canLiquidFlowIntoSide(liquidContainer, BlockFace.SOUTH))) {
                if (spreadNeighbor(dimension, src, neighborD, queue)) {
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
     * @param dimension The dimension the block is in
     * @param source    The source position of the liquid
     * @param node      The node to spread
     * @param queue     The queue to push the node into if it can spread
     * @return Whether the node could spread into the neighbour
     */
    protected boolean spreadNeighbor(Dimension dimension, Vector3ic source, LiquidNode node, LiquidQueue queue) {
        if (
            // Depth has reached zero or below, can't spread any further.
                node.depth() + 3 <= 0 ||
                // This path is longer than any existing path, so don't spread any further.
                node.length() > queue.shortestPath
        ) {
            return false;
        }

        var pos = new Vector3i(node.x(), source.y(), node.z());
        if (!canFlowInto(dimension, pos, true)) {
            // Can't flow into this block, can't spread any further.
            return false;
        }

        // Try flow down
        pos.y--;
        if (canFlowInto(dimension, pos, false)) {
            return true;
        }

        queue.pushBack(node);
        return false;
    }

    /**
     * @see #canFlowInto(Dimension, int, int, int, boolean)
     */
    protected boolean canFlowInto(Dimension dimension, Vector3ic pos, boolean sideways) {
        return canFlowInto(dimension, pos.x(), pos.y(), pos.z(), sideways);
    }

    /**
     * Checks if a liquid can flow into the block present in the world at a specific block position.
     *
     * @param dimension The dimension the block is in
     * @param x         The x coordinate of the block
     * @param y         The y coordinate of the block
     * @param z         The z coordinate of the block
     * @param sideways  Whether the flow is sideways or downwards
     * @return Whether the liquid can flow into the block
     */
    protected boolean canFlowInto(Dimension dimension, int x, int y, int z, boolean sideways) {
        var existing = dimension.getBlockState(x, y, z);
        if (existing.getBlockType() == BlockTypes.AIR ||
            existing.getBlockStateData().liquidReactionOnTouch().shouldRemoveOnTouch()) {
            return true;
        }

        if (existing.getBehavior() instanceof BlockLiquidBaseComponent) {
            if (!sideways) {
                return true;
            }
            // Can't flow into a source liquid or a different type of liquid.
            return !isSource(existing) && isSameLiquidType(existing.getBlockType());
        }

        return canBeContained() && existing.getBlockStateData().liquidReactionOnTouch().canLiquidFlowInto();
    }

    /**
     * liquidNode represents a position that is part of a flow path for a liquid.
     *
     * @param x        The x coordinate of the node
     * @param z        The z coordinate of the node
     * @param depth    The depth of the node. Byte is used to save memory
     * @param previous The previous node in the path
     */
    protected record LiquidNode(int x, int z, byte depth, LiquidNode previous) {
        /**
         * neighbours returns the four horizontal neighbours of the node with decreased depth.
         *
         * @param decay The amount to decrease the depth by
         * @return The neighbours of the node
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
         * @return The length of the path
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
         * @return The path created by the node
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
