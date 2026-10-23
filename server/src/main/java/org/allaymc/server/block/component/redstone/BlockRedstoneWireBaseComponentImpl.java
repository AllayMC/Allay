package org.allaymc.server.block.component.redstone;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.*;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.REDSTONE_SIGNAL;

/**
 * {@code BlockRedstoneWireBaseComponentImpl} computes connected dust signals from
 * external power and propagates them across horizontal and permitted diagonal wire
 * links. Direct support checks require a full upper surface. The connection rules
 * distinguish collision support from opaque obstruction so transparent supporting
 * blocks can still participate in slopes.
 *
 * <p>
 * A network refresh first gathers reachable wires and their non-wire power sources,
 * then starts every candidate signal at zero. Highest-strength entries propagate with
 * one signal unit lost per link, preventing old stored wire levels from sustaining a
 * disconnected network. Changed values are written individually before neighbor
 * notifications are queued; this is a coordinated calculation, not an atomic or
 * simultaneous world update. Output direction is calculated separately from connection
 * faces, including dot and single-endpoint line behavior, and solid neighbors receive
 * second-order notifications when signals change.
 *
 * @author daoge_cmd
 */
public class BlockRedstoneWireBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates the redstone wire component for the supplied registered block type.
     */
    public BlockRedstoneWireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places dust only above a full upper support surface and refreshes its wire
     * network after accepted placement. Direct and conducted-neighbor notifications
     * follow network evaluation; refused placement returns {@code false} before those
     * extra operations.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        BlockState below = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!below.getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
            return false;
        }

        if (!dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo)) {
            return false;
        }

        updateWireNetwork(dimension, placeBlockPos);

        updateNeighborsOnWireChange(dimension, placeBlockPos);
        return true;
    }

    /**
     * Applies inherited handling, breaks dust when lower support disappears, and
     * otherwise recalculates its connected network. A support-loss branch returns after
     * requesting the break rather than evaluating the old wire snapshot.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN && !neighbor.getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
            block.breakBlock();
            return;
        }

        updateWireNetwork(block.getDimension(), block.getPosition());
    }

    /**
     * Recalculates the wire component reachable from {@code startPos} using only
     * non-wire sources as initial power. Discovery records old strengths for comparison
     * while collecting external input and geometric links. New strengths begin at zero,
     * so a removed source cannot leave a cycle powered only by its previous stored
     * values.
     *
     * <p>
     * A highest-strength priority queue propagates candidates with one unit lost at
     * each link. Stale weaker entries are skipped, and a link updates only when it
     * improves the destination candidate. World properties are then changed one
     * position at a time, followed by queued direct and second-order notifications for
     * every changed position. The calculation provides no rollback or atomic world
     * snapshot across those reads and writes.
     */
    protected void updateWireNetwork(Dimension dimension, Vector3ic startPos) {
        // Discover the complete reachable component before changing signals so
        // old strengths serve only as comparison values, rather than becoming new external power.
        Map<Vector3i, Integer> wirePositions = new HashMap<>();
        Map<Vector3i, Integer> externalPower = new HashMap<>();
        Set<Vector3i> visited = new HashSet<>();
        Queue<Vector3i> toVisit = new LinkedList<>();

        Vector3i start = new Vector3i(startPos);
        toVisit.add(start);
        visited.add(start);

        while (!toVisit.isEmpty()) {
            Vector3i pos = toVisit.poll();
            BlockState state = dimension.getBlockState(pos);

            if (!isRedstoneWire(state)) {
                continue;
            }

            int currentPower = state.getPropertyValue(REDSTONE_SIGNAL);
            wirePositions.put(pos, currentPower);

            int extPower = calculateExternalPower(dimension, pos);
            if (extPower > 0) {
                externalPower.put(pos, extPower);
            }

            for (Vector3i connectedPos : getConnectedWirePositions(dimension, pos)) {
                if (!visited.contains(connectedPos) && isRedstoneWire(dimension.getBlockState(connectedPos))) {
                    visited.add(connectedPos);
                    toVisit.add(connectedPos);
                }
            }
        }

        // Rebuild from zero instead of retaining old dust levels. Without this
        // reset, a wire cycle could keep forwarding stale strength after its external source disappears.
        Map<Vector3i, Integer> newPowerLevels = new HashMap<>();

        for (Vector3i pos : wirePositions.keySet()) {
            newPowerLevels.put(pos, 0);
        }

        PriorityQueue<Map.Entry<Vector3i, Integer>> powerQueue = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.getValue(), a.getValue())
        );

        for (Map.Entry<Vector3i, Integer> entry : externalPower.entrySet()) {
            powerQueue.add(entry);
            newPowerLevels.put(entry.getKey(), entry.getValue());
        }

        while (!powerQueue.isEmpty()) {
            Map.Entry<Vector3i, Integer> entry = powerQueue.poll();
            Vector3i pos = entry.getKey();
            int power = entry.getValue();

            // A stronger route may improve this position while an older entry
            // remains queued. Skip the weaker entry so it cannot propagate an obsolete candidate.
            if (newPowerLevels.get(pos) > power) {
                continue;
            }

            // Each wire link consumes one signal unit before reaching its neighbor.
            int propagatedPower = power - 1;
            if (propagatedPower <= 0) {
                continue;
            }

            for (Vector3i connectedPos : getConnectedWirePositions(dimension, pos)) {
                if (wirePositions.containsKey(connectedPos)) {
                    int currentNewPower = newPowerLevels.get(connectedPos);
                    if (propagatedPower > currentNewPower) {
                        newPowerLevels.put(connectedPos, propagatedPower);
                        powerQueue.add(Map.entry(connectedPos, propagatedPower));
                    }
                }
            }
        }

        // Apply all calculated values before queuing neighbor callbacks. Those
        // callbacks then read the refreshed component rather than intermediate propagation candidates.
        Set<Vector3i> changedPositions = new HashSet<>();
        for (Map.Entry<Vector3i, Integer> entry : newPowerLevels.entrySet()) {
            Vector3i pos = entry.getKey();
            int newPower = entry.getValue();
            int oldPower = wirePositions.get(pos);

            if (newPower != oldPower) {
                dimension.updateBlockProperty(REDSTONE_SIGNAL, newPower, pos);
                changedPositions.add(pos);
            }
        }

        for (Vector3i changedPos : changedPositions) {
            updateNeighborsOnWireChange(dimension, changedPos);
        }
    }

    /**
     * Returns newly allocated candidate wire positions around the supplied coordinate.
     * Every horizontal neighbor is included regardless of its present type. An upward
     * diagonal also requires free space above this wire and a full upper support
     * surface on the adjacent block.
     *
     * <p>
     * A downward diagonal is included when the adjacent block does not block the view.
     * Obstruction requires both solidity and opacity, so transparent solid blocks do
     * not count as view blockers. Callers still test the candidates for actual dust and
     * use the resulting links for network traversal.
     */
    protected List<Vector3i> getConnectedWirePositions(Dimension dimension, Vector3ic pos) {
        List<Vector3i> positions = new ArrayList<>();
        var aboveData = dimension.getBlockState(pos.x(), pos.y() + 1, pos.z()).getBlockStateData();
        boolean aboveBlocks = aboveData.isSolid() && !aboveData.isTransparent();

        for (BlockFace face : BlockFace.getHorizontalBlockFaces()) {
            Vector3i horizontalPos = new Vector3i(face.offsetPos(pos));
            var neighborData = dimension.getBlockState(horizontalPos).getBlockStateData();
            boolean neighborCanSupport = neighborData.collisionShape().isFull(BlockFace.UP);
            boolean neighborBlocksView = neighborData.isSolid() && !neighborData.isTransparent();

            positions.add(horizontalPos);

            if (!aboveBlocks && neighborCanSupport) {
                positions.add(new Vector3i(horizontalPos.x, pos.y() + 1, horizontalPos.z));
            }

            if (!neighborBlocksView) {
                positions.add(new Vector3i(horizontalPos.x, pos.y() - 1, horizontalPos.z));
            }
        }

        return positions;
    }

    /**
     * Combines non-wire sources adjacent to a dust position without using stored dust
     * signals as fresh input. Each non-wire neighbor can contribute direct weak power.
     * A solid neighbor can additionally conduct strong power from its other sides,
     * using a helper that also skips wire sources.
     *
     * <p>
     * The maximum accepted observation becomes the initial strength for network
     * propagation. Excluding wire in both paths prevents a dust cycle, including one
     * coupled through a solid block, from keeping itself powered after the real
     * external source disappears.
     */
    protected int calculateExternalPower(Dimension dimension, Vector3ic pos) {
        int maxPower = 0;

        for (BlockFace face : BlockFace.VALUES) {
            Vector3ic neighborPos = face.offsetPos(pos);
            BlockState neighborState = dimension.getBlockState(neighborPos);

            // A dust neighbor belongs to network propagation, not external input;
            // counting its stored value here would let removed-source networks feed themselves.
            if (isRedstoneWire(neighborState)) {
                continue;
            }

            Block neighborBlock = new Block(neighborState, new Position3i(neighborPos, dimension));

            int signal = neighborState.getBehavior().getWeakPower(neighborBlock, face.opposite());
            maxPower = Math.max(maxPower, signal);

            if (neighborState.getBlockStateData().isSolid()) {
                int powerThroughBlock = getStrongPowerIntoBlock(dimension, neighborPos, face.opposite());
                maxPower = Math.max(maxPower, powerThroughBlock);
            }
        }

        return maxPower;
    }

    /**
     * Returns the maximum accepted strong power entering a solid neighbor of the wire
     * being evaluated. {@code excludeFace} removes the side facing back toward that
     * wire, and wire sources on every other face are skipped as well.
     *
     * <p>
     * The result therefore represents external conducted power for network seeding
     * rather than another dust signal waiting to be propagated. Other block behaviors
     * are queried toward the solid block, and their highest returned strength wins
     * without modifying any source state.
     */
    protected int getStrongPowerIntoBlock(Dimension dimension, Vector3ic solidPos, BlockFace excludeFace) {
        int maxPower = 0;

        for (BlockFace face : BlockFace.VALUES) {
            if (face == excludeFace) continue;

            Vector3ic checkPos = face.offsetPos(solidPos);
            BlockState state = dimension.getBlockState(checkPos);

            // Conducted input must also exclude dust, otherwise a solid block
            // could route the network's previous stored signal back into its own source set.
            if (isRedstoneWire(state)) {
                continue;
            }

            Block checkBlock = new Block(state, new Position3i(checkPos, dimension));

            int strongPower = state.getBehavior().getStrongPower(checkBlock, face.opposite());
            maxPower = Math.max(maxPower, strongPower);
        }

        return maxPower;
    }

    /**
     * Returns whether the supplied state is the registered redstone wire type.
     */
    protected boolean isRedstoneWire(BlockState state) {
        return state.getBlockType() == BlockTypes.REDSTONE_WIRE;
    }

    /**
     * Returns stored dust strength toward its support and accepted horizontal output
     * faces. Upward queries return {@code 0}, while horizontal output depends on the
     * network shape calculated by {@code isPointingTo}.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        int power = block.getPropertyValue(REDSTONE_SIGNAL);
        if (power == 0) {
            return 0;
        }

        if (face == BlockFace.DOWN) {
            return power;
        }

        if (face == BlockFace.UP) {
            return 0;
        }

        if (!isPointingTo(block, face)) {
            return 0;
        }

        return power;
    }

    /**
     * Returns whether the wire shape points toward the queried horizontal face.
     * Connection discovery samples all four horizontal directions, including permitted
     * slopes and recognized components. With no connections, the dot supplies output in
     * every queried direction reaching this helper.
     *
     * <p>
     * One connection forms a line that points both toward and opposite that endpoint.
     * Two or more connections restrict output to the connected faces.
     * {@code getWeakPower} handles vertical directions before calling this helper, so
     * its dot result is used only for horizontal output in the power path.
     */
    protected boolean isPointingTo(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        Set<BlockFace> connectedFaces = new HashSet<>();
        for (BlockFace horizontalFace : BlockFace.getHorizontalBlockFaces()) {
            if (isConnectedTo(dimension, pos, horizontalFace)) {
                connectedFaces.add(horizontalFace);
            }
        }

        if (connectedFaces.isEmpty()) {
            return true;
        }

        if (connectedFaces.size() == 1) {
            BlockFace singleConnection = connectedFaces.iterator().next();
            return face == singleConnection || face == singleConnection.opposite();
        }

        return connectedFaces.contains(face);
    }

    /**
     * Returns whether a horizontal face connects dust to a neighboring wire or accepted
     * component. Same-level dust connects immediately. A neighboring repeater connects
     * only along its input-output axis, while the component connection tag handles
     * other explicitly accepted types.
     *
     * <p>
     * If no same-level source matches, slope checks inspect dust above or below the
     * adjacent block. Upward links require that block's full upper surface and an
     * unblocked space above this wire. Downward links require a non-obstructing
     * neighbor; both obstruction tests require solidity and opacity together.
     */
    protected boolean isConnectedTo(Dimension dimension, Vector3ic pos, BlockFace face) {
        Vector3ic neighborPos = face.offsetPos(pos);
        BlockState neighborState = dimension.getBlockState(neighborPos);

        if (isRedstoneWire(neighborState)) {
            return true;
        }

        if (isRepeater(neighborState)) {
            BlockFace repeaterFacing = getRepeaterFacing(neighborState);
            return face == repeaterFacing || face == repeaterFacing.opposite();
        }

        if (shouldWireConnectTo(neighborState)) {
            return true;
        }

        var neighborData = neighborState.getBlockStateData();
        boolean neighborCanSupport = neighborData.collisionShape().isFull(BlockFace.UP);
        boolean neighborBlocksView = neighborData.isSolid() && !neighborData.isTransparent();

        var aboveData = dimension.getBlockState(pos.x(), pos.y() + 1, pos.z()).getBlockStateData();
        boolean aboveBlocks = aboveData.isSolid() && !aboveData.isTransparent();

        if (!aboveBlocks && neighborCanSupport &&
            isRedstoneWire(dimension.getBlockState(neighborPos.x(), neighborPos.y() + 1, neighborPos.z()))) {
            return true;
        }

        if (!neighborBlocksView &&
            isRedstoneWire(dimension.getBlockState(neighborPos.x(), neighborPos.y() - 1, neighborPos.z()))) {
            return true;
        }

        return false;
    }

    /**
     * Returns whether the state's type carries the explicit wire-connection block tag.
     */
    protected boolean shouldWireConnectTo(BlockState state) {
        return state.getBlockType().hasBlockTag(BlockTags.REDSTONE_WIRE_CONNECT_TO);
    }

    /**
     * Returns whether the state is a powered or unpowered repeater variant.
     */
    protected boolean isRepeater(BlockState state) {
        var type = state.getBlockType();
        return type == BlockTypes.POWERED_REPEATER ||
               type == BlockTypes.UNPOWERED_REPEATER;
    }

    /**
     * Returns the repeater output face opposite its stored cardinal input direction.
     * Wire uses this decoded axis to accept front and back connections while rejecting
     * repeater side connections.
     */
    protected BlockFace getRepeaterFacing(BlockState state) {
        var direction = state.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        return BlockFace.from(direction).opposite();
    }

    /**
     * Returns the same stored strength and directional restrictions as
     * {@code getWeakPower}. Conducted-input scans in this component explicitly skip
     * dust so this strong output cannot become a self-sustaining external source.
     */
    @Override
    public int getStrongPower(Block block, BlockFace face) {
        return getWeakPower(block, face);
    }

    /**
     * Notifies circuits affected by a removed wire and refreshes diagonal wire
     * components after inherited replacement handling. Direct and solid-neighbor
     * notifications cover ordinary adjacent circuits, but a slope endpoint can lie
     * outside that immediate neighborhood.
     *
     * <p>
     * The callback therefore inspects every adjacent horizontal column one block above
     * and below, refreshing each actual wire found there. It does not require the old
     * wire's slope-visibility rule for this removal scan, allowing components separated
     * by the removed segment to recalculate their remaining external sources.
     */
    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);

        Dimension dimension = oldBlock.getDimension();
        Vector3ic pos = oldBlock.getPosition();

        updateNeighborsOnWireChange(dimension, pos);

        // Direct neighbor notifications cannot reach diagonal slope endpoints.
        // Refresh both neighboring heights explicitly so removing this segment also updates separated components.
        for (BlockFace face : BlockFace.getHorizontalBlockFaces()) {
            Vector3ic horizontalPos = face.offsetPos(pos);

            Vector3i upPos = new Vector3i(horizontalPos.x(), pos.y() + 1, horizontalPos.z());
            BlockState upState = dimension.getBlockState(upPos);
            if (isRedstoneWire(upState)) {
                updateWireNetwork(dimension, upPos);
            }

            Vector3i downPos = new Vector3i(horizontalPos.x(), pos.y() - 1, horizontalPos.z());
            BlockState downState = dimension.getBlockState(downPos);
            if (isRedstoneWire(downState)) {
                updateWireNetwork(dimension, downPos);
            }
        }
    }

    /**
     * Notifies direct neighbors and the surroundings of each adjacent solid block.
     * Circuits beyond a solid neighbor can observe changed conducted power even though
     * they are not directly adjacent to the changed dust position.
     */
    protected void updateNeighborsOnWireChange(Dimension dimension, Vector3ic pos) {
        dimension.updateAround(pos);

        for (BlockFace face : BlockFace.VALUES) {
            Vector3ic neighborPos = face.offsetPos(pos);
            BlockState neighborState = dimension.getBlockState(neighborPos);

            if (neighborState.getBlockStateData().isSolid()) {
                dimension.updateAround(neighborPos);
            }
        }
    }

    /**
     * Returns a single redstone dust item for the wire block.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.REDSTONE.createItemStack());
    }
}
