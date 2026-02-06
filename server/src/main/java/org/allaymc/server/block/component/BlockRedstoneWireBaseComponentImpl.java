package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.NeighborUpdate;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.*;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.REDSTONE_SIGNAL;

/**
 * Implementation of redstone wire (dust).
 * Signal propagates horizontally and diagonally with decay.
 * Uses a coordinated update algorithm to ensure all connected wires
 * update simultaneously when power source is removed.
 *
 * @author daoge_cmd
 */
public class BlockRedstoneWireBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockRedstoneWireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        // Redstone wire requires a block with full top face below
        BlockState below = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!below.getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
            return false;
        }

        if (!dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo)) {
            return false;
        }

        // Update the wire network starting from this position
        updateWireNetwork(dimension, placeBlockPos);

        // Update neighbors and second-order neighbors (for weak power through solid blocks)
        updateNeighborsOnWireChange(dimension, placeBlockPos);
        return true;
    }

    @Override
    public void onNeighborUpdate(NeighborUpdate context) {
        super.onNeighborUpdate(context);

        var block = context.block();
        var neighbor = context.neighbor();
        var face = context.face();

        // Break if block below no longer has full top face
        if (face == BlockFace.DOWN && !neighbor.getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
            block.breakBlock();
            return;
        }

        // Update the entire connected wire network
        updateWireNetwork(block.getDimension(), block.getPosition());
    }

    /**
     * Updates the entire connected redstone wire network.
     * This uses a two-phase approach:
     * 1. Collect all connected wires and find power from external sources
     * 2. Propagate power through the network using BFS from highest power positions
     *
     * @param dimension the dimension
     * @param startPos  the position where update started
     */
    protected void updateWireNetwork(Dimension dimension, Vector3ic startPos) {
        // Phase 1: Collect all connected wires and their external power sources
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

            // Calculate power from external (non-wire) sources
            int extPower = calculateExternalPower(dimension, pos);
            if (extPower > 0) {
                externalPower.put(pos, extPower);
            }

            // Add connected wires to visit
            for (Vector3i connectedPos : getConnectedWirePositions(dimension, pos)) {
                if (!visited.contains(connectedPos) && isRedstoneWire(dimension.getBlockState(connectedPos))) {
                    visited.add(connectedPos);
                    toVisit.add(connectedPos);
                }
            }
        }

        // Phase 2: Calculate new power levels using BFS from power sources
        Map<Vector3i, Integer> newPowerLevels = new HashMap<>();

        // Initialize all wires to 0
        for (Vector3i pos : wirePositions.keySet()) {
            newPowerLevels.put(pos, 0);
        }

        // Use a priority queue to process from highest power first
        PriorityQueue<Map.Entry<Vector3i, Integer>> powerQueue = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.getValue(), a.getValue())
        );

        // Add all positions with external power sources
        for (Map.Entry<Vector3i, Integer> entry : externalPower.entrySet()) {
            powerQueue.add(entry);
            newPowerLevels.put(entry.getKey(), entry.getValue());
        }

        // Propagate power through the network
        while (!powerQueue.isEmpty()) {
            Map.Entry<Vector3i, Integer> entry = powerQueue.poll();
            Vector3i pos = entry.getKey();
            int power = entry.getValue();

            // Skip if this position already has higher power
            if (newPowerLevels.get(pos) > power) {
                continue;
            }

            // Propagate to connected wires with decay
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

        // Phase 3: Apply changes and trigger updates
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

        // Trigger neighbor updates for all changed positions
        // Also update second-order neighbors through solid blocks
        for (Vector3i changedPos : changedPositions) {
            updateNeighborsOnWireChange(dimension, changedPos);
        }
    }

    /**
     * Gets all positions that could be connected wires (horizontal and diagonal).
     * <p>
     * For redstone wire on stairs/slopes:
     * - Upward diagonal: A wire can see a wire diagonally above only if the block above us doesn't block the view
     * (transparent blocks like glass don't block) AND the neighbor block can support wire (has full top face)
     * - Downward diagonal: A wire can see a wire diagonally below only if the neighbor block doesn't block
     * line of sight (transparent blocks like glass don't block)
     */
    protected List<Vector3i> getConnectedWirePositions(Dimension dimension, Vector3ic pos) {
        List<Vector3i> positions = new ArrayList<>();
        var aboveData = dimension.getBlockState(pos.x(), pos.y() + 1, pos.z()).getBlockStateData();
        // Block above only blocks if it's solid AND not transparent (e.g., glass doesn't block)
        boolean aboveBlocks = aboveData.isSolid() && !aboveData.isTransparent();

        for (BlockFace face : BlockFace.getHorizontalBlockFaces()) {
            Vector3i horizontalPos = new Vector3i(face.offsetPos(pos));
            var neighborData = dimension.getBlockState(horizontalPos).getBlockStateData();
            // Neighbor can support wire if it has a full top face (e.g., glass can support wire)
            boolean neighborCanSupport = neighborData.collisionShape().isFull(BlockFace.UP);
            // Neighbor blocks line of sight if it's solid AND not transparent
            boolean neighborBlocksView = neighborData.isSolid() && !neighborData.isTransparent();

            // Always check horizontal neighbor (same Y level)
            positions.add(horizontalPos);

            // Upward diagonal: wire at Y+1 of horizontal neighbor
            // Can only connect if block above us doesn't block AND neighbor can support wire
            if (!aboveBlocks && neighborCanSupport) {
                positions.add(new Vector3i(horizontalPos.x, pos.y() + 1, horizontalPos.z));
            }

            // Downward diagonal: wire at Y-1 of horizontal neighbor
            // Can only connect if neighbor block doesn't block line of sight
            if (!neighborBlocksView) {
                positions.add(new Vector3i(horizontalPos.x, pos.y() - 1, horizontalPos.z));
            }
        }

        return positions;
    }

    /**
     * Calculates power from external (non-wire) sources only.
     *
     * @param dimension the dimension
     * @param pos       the position of the wire
     * @return the power level from external sources
     */
    protected int calculateExternalPower(Dimension dimension, Vector3ic pos) {
        int maxPower = 0;

        // Check all 6 faces for power sources
        for (BlockFace face : BlockFace.VALUES) {
            Vector3ic neighborPos = face.offsetPos(pos);
            BlockState neighborState = dimension.getBlockState(neighborPos);

            // Skip redstone wire - we only want external sources
            if (isRedstoneWire(neighborState)) {
                continue;
            }

            Block neighborBlock = new Block(neighborState, new Position3i(neighborPos, dimension));

            // Check direct weak power
            int signal = neighborState.getBehavior().getWeakPower(neighborBlock, face.opposite());
            maxPower = Math.max(maxPower, signal);

            // Check power through solid blocks (only strong power can conduct through)
            if (neighborState.getBlockStateData().isSolid()) {
                int powerThroughBlock = getStrongPowerIntoBlock(dimension, neighborPos, face.opposite());
                maxPower = Math.max(maxPower, powerThroughBlock);
            }
        }

        return maxPower;
    }

    /**
     * Gets strong power flowing into a solid block from any direction.
     * Only strong power can be conducted through solid blocks.
     */
    protected int getStrongPowerIntoBlock(Dimension dimension, Vector3ic solidPos, BlockFace excludeFace) {
        int maxPower = 0;

        for (BlockFace face : BlockFace.VALUES) {
            if (face == excludeFace) continue;

            Vector3ic checkPos = face.offsetPos(solidPos);
            BlockState state = dimension.getBlockState(checkPos);

            // Skip redstone wire for power calculation
            if (isRedstoneWire(state)) {
                continue;
            }

            Block checkBlock = new Block(state, new Position3i(checkPos, dimension));

            // Only strong power can be conducted through solid blocks
            int strongPower = state.getBehavior().getStrongPower(checkBlock, face.opposite());
            maxPower = Math.max(maxPower, strongPower);
        }

        return maxPower;
    }

    protected boolean isRedstoneWire(BlockState state) {
        return state.getBlockType() == BlockTypes.REDSTONE_WIRE;
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        int power = block.getPropertyValue(REDSTONE_SIGNAL);
        if (power == 0) {
            return 0;
        }

        // In Allay, face represents the direction power flows TO (via face.opposite() call)
        // Wire powers the block it's placed on (block below), so face=DOWN means power to block below
        if (face == BlockFace.DOWN) {
            return power;
        }

        // Wire doesn't power block above it
        if (face == BlockFace.UP) {
            return 0;
        }

        // Check if wire is pointing towards this direction (horizontal)
        if (!isPointingTo(block, face)) {
            return 0;
        }

        return power;
    }

    /**
     * Checks if the redstone wire is pointing towards a specific direction.
     * Based on MC Wiki:
     * - No connections: forms a dot, points all 4 directions
     * - 1 connection: forms a line, points toward AND away from that neighbor
     * - 2+ connections (line, L, T, or +): points to all connected directions
     */
    protected boolean isPointingTo(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        // Collect all horizontal connections
        Set<BlockFace> connectedFaces = new HashSet<>();
        for (BlockFace horizontalFace : BlockFace.getHorizontalBlockFaces()) {
            if (isConnectedTo(dimension, pos, horizontalFace)) {
                connectedFaces.add(horizontalFace);
            }
        }

        // If no connections, wire forms a dot and points to all 4 directions
        if (connectedFaces.isEmpty()) {
            return true;
        }

        // Single connection: wire forms a line pointing toward AND away from the connection
        if (connectedFaces.size() == 1) {
            BlockFace singleConnection = connectedFaces.iterator().next();
            return face == singleConnection || face == singleConnection.opposite();
        }

        // 2+ connections (line, L-shape, T-shape, or cross):
        // Wire points to all connected directions
        return connectedFaces.contains(face);
    }

    /**
     * Checks if the redstone wire connects to a specific horizontal direction.
     * This determines the visual shape and power output direction of the wire.
     */
    protected boolean isConnectedTo(Dimension dimension, Vector3ic pos, BlockFace face) {
        Vector3ic neighborPos = face.offsetPos(pos);
        BlockState neighborState = dimension.getBlockState(neighborPos);

        // Connect to other redstone wire at same level
        if (isRedstoneWire(neighborState)) {
            return true;
        }

        // Special handling for repeater - only connect to front/back, not sides
        // (Comparator connects on all sides because it accepts side inputs)
        if (isRepeater(neighborState)) {
            BlockFace repeaterFacing = getRepeaterFacing(neighborState);
            // Connect only if wire is at the input (back) or output (front) of the repeater
            return face == repeaterFacing || face == repeaterFacing.opposite();
        }

        // Connect to other redstone components that wire actively connects to
        if (shouldWireConnectTo(neighborState)) {
            return true;
        }

        // Check for diagonal connections (wire going up/down slopes)
        var neighborData = neighborState.getBlockStateData();
        boolean neighborCanSupport = neighborData.collisionShape().isFull(BlockFace.UP);
        boolean neighborBlocksView = neighborData.isSolid() && !neighborData.isTransparent();

        var aboveData = dimension.getBlockState(pos.x(), pos.y() + 1, pos.z()).getBlockStateData();
        boolean aboveBlocks = aboveData.isSolid() && !aboveData.isTransparent();

        // Wire above the neighbor block (upward slope)
        if (!aboveBlocks && neighborCanSupport &&
            isRedstoneWire(dimension.getBlockState(neighborPos.x(), neighborPos.y() + 1, neighborPos.z()))) {
            return true;
        }

        // Wire below the neighbor block (downward slope)
        if (!neighborBlocksView &&
            isRedstoneWire(dimension.getBlockState(neighborPos.x(), neighborPos.y() - 1, neighborPos.z()))) {
            return true;
        }

        return false;
    }

    /**
     * Checks if a block is a redstone component that wire should actively connect to.
     * This includes power sources and redstone mechanisms, but not passive receivers
     * like redstone lamps, dispensers, or note blocks.
     */
    protected boolean shouldWireConnectTo(BlockState state) {
        return state.getBlockType().hasBlockTag(BlockTags.REDSTONE_WIRE_CONNECT_TO);
    }

    /**
     * Checks if a block is a repeater.
     */
    protected boolean isRepeater(BlockState state) {
        var type = state.getBlockType();
        return type == BlockTypes.POWERED_REPEATER ||
               type == BlockTypes.UNPOWERED_REPEATER;
    }

    /**
     * Gets the facing direction (output direction) of a repeater.
     * Note: MINECRAFT_CARDINAL_DIRECTION stores the input direction, so we return its opposite.
     */
    protected BlockFace getRepeaterFacing(BlockState state) {
        var direction = state.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        return BlockFace.from(direction).opposite();
    }

    @Override
    public int getStrongPower(Block block, BlockFace face) {
        return getWeakPower(block, face);
    }

    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);

        Dimension dimension = oldBlock.getDimension();
        Vector3ic pos = oldBlock.getPosition();

        // Update neighbors and second-order neighbors when wire is removed
        updateNeighborsOnWireChange(dimension, pos);

        // Also update diagonal wire neighbors at Y+1 and Y-1
        // because updateAround only updates the 6 directly adjacent blocks
        for (BlockFace face : BlockFace.getHorizontalBlockFaces()) {
            Vector3ic horizontalPos = face.offsetPos(pos);

            // Check wire at Y+1 (diagonally above)
            Vector3i upPos = new Vector3i(horizontalPos.x(), pos.y() + 1, horizontalPos.z());
            BlockState upState = dimension.getBlockState(upPos);
            if (isRedstoneWire(upState)) {
                updateWireNetwork(dimension, upPos);
            }

            // Check wire at Y-1 (diagonally below)
            Vector3i downPos = new Vector3i(horizontalPos.x(), pos.y() - 1, horizontalPos.z());
            BlockState downState = dimension.getBlockState(downPos);
            if (isRedstoneWire(downState)) {
                updateWireNetwork(dimension, downPos);
            }
        }
    }

    /**
     * Updates neighbors and second-order neighbors when wire is placed or removed.
     * This is necessary for weak power propagation through solid blocks.
     * <p>
     * When wire changes at position P:
     * 1. Update all 6 direct neighbors
     * 2. For each solid neighbor S, update blocks around S - this handles weak power
     */
    protected void updateNeighborsOnWireChange(Dimension dimension, Vector3ic pos) {
        // Update direct neighbors
        dimension.updateAround(pos);

        // Update second-order neighbors through solid blocks (for weak power)
        // When a solid block's power state changes, blocks on the other side need to know
        for (BlockFace face : BlockFace.VALUES) {
            Vector3ic neighborPos = face.offsetPos(pos);
            BlockState neighborState = dimension.getBlockState(neighborPos);

            // If neighbor is a solid block, update blocks around it
            // This notifies components (like lamps) that receive weak power through this block
            if (neighborState.getBlockStateData().isSolid()) {
                dimension.updateAround(neighborPos);
            }
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.REDSTONE.createItemStack());
    }
}
