package org.allaymc.server.block.component.liquid;

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
import org.allaymc.server.block.component.BlockBaseComponentImpl;
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
 * {@code BlockLiquidBaseComponentImpl} implements shared liquid scheduling, depth decay,
 * directional spreading, and entity flow vectors. A liquid block obtains supply from above or
 * from a stronger horizontal neighbor, while liquid-family checks prevent one liquid from
 * sustaining another. Placement and neighbor changes request a scheduled update using the
 * implementation's flow delay.
 *
 * <p>
 * The update separates unsupported decay from spreading. Supported liquid first considers
 * downward flow, then searches horizontal paths toward lower ground and falls back to outward
 * spreading when no path exists. Containing blocks restrict exits on the initial step, and
 * cancellable flow or decay proposals precede their associated writes. Path nodes keep
 * predecessor identity so selected routes recover their first destination without changing
 * world blocks during exploration.
 *
 * @author daoge_cmd
 */
@Slf4j
public abstract class BlockLiquidBaseComponentImpl extends BlockBaseComponentImpl implements BlockLiquidBaseComponent {
    /**
     * Creates the liquid behavior component for the supplied block type.
     */
    public BlockLiquidBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Processes source formation followed by the liquid snapshot's normal update. Formation
     * requires depth {@code 7}, two matching horizontal sources, and a lower position rejected
     * by the sideways flow predicate. A cancelled formation event ends this invocation before
     * ordinary flow runs.
     *
     * <p>
     * An accepted proposal writes a source through {@code setLiquid}, but {@code updateLiquid}
     * still receives {@code block}'s original state. The later calculation therefore uses the
     * supplied snapshot rather than rereading the newly written source. Callers provide the
     * block and layer selected by scheduled-update dispatch.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        var pos = block.getPosition();
        var dimension = block.getDimension();
        if (canFormSource() && getDepth(block.getBlockState()) == 7) {
            // Depth seven identifies the horizontal flow state eligible for source formation.
            var count = 0;
            for (var face : BlockFace.getHorizontalBlockFaces()) {
                var neighbor = block.offsetPos(face);
                if (isSameLiquidType(neighbor.getBlockType()) && isSource(neighbor.getBlockState())) {
                    count++;
                }
            }

            if (count >= 2) {
                if (!canFlowInto(dimension, BlockFace.DOWN.offsetPos(pos), true)) {
                    // A lower matching source rejects sideways entry; a lower flowing state does not.
                    // Reuse that predicate to gate source formation alongside solid support.
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

    /**
     * Dispatches the base placement hook and requests a delayed liquid update. The supplied
     * block still describes the replaced state, so this hook schedules by position rather than
     * spreading liquid before placement completes.
     */
    @Override
    public void onPlace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.onPlace(block, newBlockState, placementInfo);
        tryScheduleLiquidUpdate(block);
    }

    /**
     * Dispatches the base neighbor hook and requests a liquid update at {@code block}'s
     * position. An update already scheduled for that position remains in place instead of
     * receiving another delay entry.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        tryScheduleLiquidUpdate(block);
    }

    /**
     * Schedules a delayed liquid update when the position has no existing scheduled entry. The
     * delay comes from {@code getFlowSpeed} for the current dimension; this helper does not
     * replace an earlier scheduled update.
     */
    protected void tryScheduleLiquidUpdate(Block current) {
        var blockUpdateManager = current.getDimension().getBlockUpdateManager();
        // Preserve an existing update deadline so repeated neighbor changes do not postpone
        // or duplicate the liquid work already queued for this position.
        if (!blockUpdateManager.hasScheduledBlockUpdate(current.getPosition())) {
            blockUpdateManager.scheduleBlockUpdateInDelay(current.getPosition(), getFlowSpeed(current.getDimension().getDimensionType()));
        }
    }

    /**
     * Returns an empty drop set because liquid blocks produce no items.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Collections.emptySet();
    }

    /**
     * Returns whether liquid contact can clear accumulated entity fall damage.
     */
    @Override
    public boolean canResetFallDamage() {
        return true;
    }

    /**
     * Calculates the normalized liquid-current vector used by {@link
     * AllayEntityPhysicsEngine}. Matching neighbors contribute their decay difference; an
     * enterable nonliquid neighbor also exposes matching liquid one block below. The result is
     * a new vector, and a zero current remains zero.
     *
     * <p>
     * Falling liquid adds a downward contribution when a horizontal neighbor and the block
     * above it both reject sideways entry. The calculation normalizes the horizontal
     * contribution before adding that downward term, then normalizes the final vector without
     * turning a zero vector into undefined components.
     */
    public Vector3d calculateFlowVector(Dimension dimension, int x, int y, int z, BlockState current) {
        // TODO: Invalidate a position-based flow cache when relevant liquid or neighbor states change.
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

                // Account for the drop behind an enterable side block so lower
                // matching liquid contributes a horizontal pull instead of disappearing from the current calculation.
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
                    // Preserve a zero horizontal current before adding the downward term;
                    // normalizing zero directly would propagate undefined components into entity motion.
                    MathUtils.normalizeIfNotZero(vector);
                    // Give downward pull a six-unit weight before final normalization.
                    vector.add(0, -6, 0);
                    break;
                }
            }
        }

        // A motionless liquid must return zero without introducing undefined velocity components.
        return MathUtils.normalizeIfNotZero(vector);
    }

    /**
     * Returns the decay contribution for a matching liquid state. Falling states contribute
     * {@code 0}, horizontal states use their encoded depth difference, and other liquid
     * families or ordinary blocks return {@code -1}.
     */
    protected int getEffectiveFlowDecay(BlockState liquid) {
        if (!isSameLiquidType(liquid.getBlockType())) {
            return -1;
        }

        return isFalling(liquid) ? 0 : 8 - getDepth(liquid);
    }

    /**
     * Updates the supplied liquid snapshot by decay or directional spreading. A nonsource
     * without a qualifying supply proposes a shallower state, or {@code null} for complete
     * removal, and returns after that cancellable decay attempt. Contained liquid reads its
     * layer-zero container to constrain exits.
     *
     * <p>
     * Supported liquid tries downward flow before horizontal spreading. Horizontal flow
     * requires enough remaining depth and either a source or no enterable block below. The
     * path search favors its shortest discovered routes to lower ground; an empty search falls
     * back to all permitted horizontal exits. Each destination still passes through {@code
     * flowInto}, which can reject or cancel an individual write.
     */
    protected void updateLiquid(Dimension dimension, Vector3ic pos, BlockState liquid, int layer) {
        if (!isSource(liquid) && !hasSupplyLiquidAround(dimension, pos, liquid)) {
            BlockState newLiquid = null;
            // The decay cutoff removes shallow unsupported liquid outright; deeper states
            // lose twice the dimension-specific horizontal decay before their next update.
            if (getDepth(liquid) - 4 > 0) {
                newLiquid = getLiquidBlockState(getDepth(liquid) - 2 * getFlowDecay(dimension.getDimensionType()), false);
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
        var decay = getFlowDecay(dimension.getDimensionType());
        if (depth <= decay) {
            return;
        }
        if (isSource(liquid) || !canFlowBelow) {
            var paths = calculateLiquidPaths(dimension, pos, liquid, liquidContainer);
            // Without a discovered descent, allow every permitted horizontal exit so
            // liquid can still spread across a level surface instead of stopping.
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
     * Attempts horizontal flow through every permitted source exit. {@code liquidContainer}
     * may be {@code null} for layer-zero liquid; a nonnull container supplies side permissions
     * and makes the source layer {@code 1}.
     */
    protected void spreadOutwards(Dimension dimension, Vector3ic src, BlockState liquid, BlockState liquidContainer) {
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            if (liquidContainer == null || liquidContainer.getBehavior().canLiquidFlowIntoSide(liquidContainer, face)) {
                flowInto(dimension, src, liquidContainer == null ? 0 : 1, liquid, face.offsetPos(src), false);
            }
        }
    }

    /**
     * Returns whether a matching neighboring liquid sustains the supplied state. The block
     * below is excluded; liquid above always qualifies after containment checks, while
     * horizontal neighbors must be sources or have greater effective depth.
     *
     * <p>
     * For layer-one neighbors, the containing block must permit flow through the face supplied
     * to its side predicate. The method examines the dimensions' current liquid states rather
     * than treating every adjacent waterlogged block as an unconditional supply. A missing
     * liquid or another liquid family cannot sustain this state.
     */
    protected boolean hasSupplyLiquidAround(Dimension dimension, Vector3ic pos, BlockState liquid) {
        for (var face : BlockFace.VALUES) {
            if (face == BlockFace.DOWN) {
                // A lower liquid cannot replenish this level through upward flow.
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
                // A layer-one liquid only supplies neighboring flow through an open container
                // side, so waterlogged blocks with closed sides cannot sustain this state.
                var layer0 = dimension.getBlockState(neighborPos);
                if (!layer0.getBehavior().canLiquidFlowIntoSide(layer0, face)) {
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
     * Attempts one liquid destination update and reports whether the target accepts the flow.
     * Horizontal flow loses the dimension's decay; falling flow retains the supplied depth. A
     * matching liquid already as deep, or already falling, returns {@code true} without a new
     * write or event.
     *
     * <p>
     * A different destination liquid receives a hardening attempt and this method returns
     * {@code false}, even when hardening succeeds. Other targets must be air, removable on
     * contact, or eligible empty containers. A cancellable flow event precedes destruction and
     * liquid placement; accepted removable targets follow their configured break reaction
     * before {@code setLiquid} chooses the destination layer.
     */
    protected boolean flowInto(Dimension dimension, Vector3ic src, int srcLayer, BlockState liquid, Vector3ic pos, boolean falling) {
        var newDepth = getDepth(liquid);
        if (!falling) {
            newDepth -= getFlowDecay(dimension.getDimensionType());
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

            // Give the existing liquid ownership of the hardening proposal because its
            // implementation determines the solid replacement and the position that changes.
            existingLiquidBaseComponent.tryHarden(
                    new Block(existing, new Position3i(pos, dimension), 0),
                    new Block(liquid, new Position3i(src, dimension), 0)
            );
            return false;
        }

        var liquidReactionOnTouch = existing.getBlockStateData().liquidReactionOnTouch();
        var canContainLiquid =
                canBeContained() &&
                liquidReactionOnTouch.canLiquidFlowInto();
        if (canContainLiquid) {
            if (dimension.getLiquid(pos).right() != null) {
                return false;
            }
        }

        // Keep an occupied source container intact even when its contact reaction would
        // otherwise remove the layer-zero block during this incoming flow attempt.
        if (existing.getBlockStateData().canContainLiquidSource() && dimension.getLiquid(pos).right() != null) {
            return false;
        }

        var removedOnTouch = liquidReactionOnTouch.shouldRemoveOnTouch();
        if (!(existing.getBlockType() == BlockTypes.AIR || removedOnTouch) && (!canContainLiquid || !liquidReactionOnTouch.canLiquidFlowInto())) {
            return false;
        }

        var event = new LiquidFlowEvent(
                new Block(getLiquidBlockState(newDepth, falling), new Position3i(src, dimension), srcLayer),
                pos, existing
        );
        if (!event.call()) {
            return false;
        }

        // Dispatch acceptance precedes both removal and liquid placement, so cancellation
        // leaves this target intact instead of destroying it before the proposal is decided.
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
     * Finds horizontal routes from {@code src} to enterable blocks with an opening below. The
     * search returns destination arrays excluding the source; an empty list tells {@code
     * updateLiquid} to use unrestricted outward spreading. World blocks remain unchanged
     * during exploration.
     *
     * <p>
     * The queue visits predecessor-linked nodes in insertion order, decreases simulated depth
     * by twice the horizontal decay, and stops extending routes beyond the shortest descent
     * discovered. A nonnull {@code liquidContainer} restricts the source's first exits only.
     * Later nodes use ordinary liquid-entry checks, and the caller selects the first step of
     * the shortest returned paths.
     */
    protected List<Vector3ic[]> calculateLiquidPaths(Dimension dimension, Vector3ic src, BlockState liquid, BlockState liquidContainer) {
        var queue = new LiquidQueue();
        queue.pushBack(new LiquidNode(src.x(), src.z(), (byte) getDepth(liquid), null));
        var decay = getFlowDecay(dimension.getDimensionType());

        var paths = new ArrayList<Vector3ic[]>();
        var first = true;

        while (queue.length() != 0) {
            var node = queue.front();
            // Simulated paths spend twice the ordinary horizontal decay, limiting the
            // descent search independently of the actual flow written for its first step.
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
     * Evaluates one simulated path node and reports whether it reaches an opening below. The
     * node is rejected when its remaining search depth crosses the cutoff, its path exceeds
     * {@code shortestPath}, or sideways entry is blocked.
     *
     * <p>
     * An eligible descent returns {@code true} without enqueuing the node. An eligible level
     * continuation is appended to {@code queue} and returns {@code false}; this result
     * therefore describes a completed descent rather than every successful queue extension.
     * The helper never writes liquid into the dimension while calculating a route.
     */
    protected boolean spreadNeighbor(Dimension dimension, Vector3ic source, LiquidNode node, LiquidQueue queue) {
        if (
            // The search allows a three-level margin after simulated decay before pruning
            // a node; the shortest descent bound then prevents exploring longer alternatives.
                node.depth() + 3 <= 0 ||
                node.length() > queue.shortestPath
        ) {
            return false;
        }

        var pos = new Vector3i(node.x(), source.y(), node.z());
        if (!canFlowInto(dimension, pos, true)) {
            return false;
        }

        pos.y--;
        if (canFlowInto(dimension, pos, false)) {
            return true;
        }

        queue.pushBack(node);
        return false;
    }

    /**
     * Returns whether the coordinate-based entry predicate accepts {@code pos}. {@code
     * sideways} distinguishes horizontal entry from downward entry, including the different
     * rules for existing liquids; this check itself does not place liquid.
     */
    protected boolean canFlowInto(Dimension dimension, Vector3ic pos, boolean sideways) {
        return canFlowInto(dimension, pos.x(), pos.y(), pos.z(), sideways);
    }

    /**
     * Returns whether a target is eligible for a liquid-entry attempt. Air and removable
     * targets qualify immediately; downward checks accept any liquid, while horizontal checks
     * require a matching nonsource liquid. Other blocks qualify only when this liquid and the
     * block's contact reaction permit containment.
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
            return !isSource(existing) && isSameLiquidType(existing.getBlockType());
        }

        return canBeContained() && existing.getBlockStateData().liquidReactionOnTouch().canLiquidFlowInto();
    }

    /**
     * {@code LiquidNode} represents one horizontal search position with its remaining
     * simulated depth and predecessor. {@code previous} links back to the source node,
     * allowing route extraction without storing a separate path at every extension.
     * @param x horizontal x coordinate
     * @param z horizontal z coordinate
     * @param depth remaining simulated depth
     * @param previous predecessor node, or {@code null} at the source
     */
    protected record LiquidNode(int x, int z, byte depth, LiquidNode previous) {
        /**
         * Returns west, east, north, and south search nodes in that order. Each node subtracts
         * {@code decay} from its simulated depth and retains this node as its predecessor
         * without accessing world state.
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
         * Returns the number of predecessor steps between this node and its source.
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
         * Returns a new source-to-destination coordinate array excluding the source node.
         * Every coordinate uses {@code sourcePos}'s vertical level; both the array and its
         * mutable coordinate instances are newly allocated for this extraction.
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
     * {@code LiquidQueue} holds pending path nodes in insertion order and the shortest
     * discovered descent length. The path search removes each front node before examining its
     * neighbors and uses {@code shortestPath} to prune longer alternatives.
     */
    protected static final class LiquidQueue {

        /**
         * Pending horizontal search nodes.
         */
        private final ArrayList<LiquidNode> nodes;
        /**
         * {@code shortestPath} bounds further node expansion after a descent is found. {@code
         * Integer.MAX_VALUE} initially permits every route within the simulated depth limit.
         */
        private int shortestPath;

        /**
         * Creates an empty path queue without a discovered descent bound.
         */
        public LiquidQueue() {
            nodes = new ArrayList<>();
            shortestPath = Integer.MAX_VALUE;
        }

        /**
         * Appends the supplied search node after the currently pending nodes.
         */
        public void pushBack(LiquidNode node) {
            nodes.add(node);
        }

        /**
         * Removes and returns the next pending path node in insertion order. Callers must
         * first establish that the queue is nonempty; an empty queue propagates the list's
         * missing-element failure.
         */
        public LiquidNode front() {
            return nodes.removeFirst();
        }

        /**
         * Returns the number of search nodes still awaiting path expansion.
         */
        public int length() {
            return nodes.size();
        }
    }
}
