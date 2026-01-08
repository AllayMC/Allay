package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.*;

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
        // Redstone wire requires a solid block below
        BlockState below = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!below.getBlockStateData().isSolid()) {
            return false;
        }

        if (!dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo)) {
            return false;
        }

        // Update the wire network starting from this position
        updateWireNetwork(dimension, placeBlockPos);
        return true;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        // Break if block below is removed
        if (face == BlockFace.DOWN && !neighbor.getBlockStateData().isSolid()) {
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
            addConnectedWires(dimension, pos, visited, toVisit);
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
        for (Vector3i pos : changedPositions) {
            dimension.updateAround(pos);
        }
    }

    /**
     * Adds all connected wire positions to the visit queue.
     */
    protected void addConnectedWires(Dimension dimension, Vector3i pos, Set<Vector3i> visited, Queue<Vector3i> toVisit) {
        for (Vector3i connectedPos : getConnectedWirePositions(dimension, pos)) {
            if (!visited.contains(connectedPos)) {
                BlockState state = dimension.getBlockState(connectedPos);
                if (isRedstoneWire(state)) {
                    visited.add(connectedPos);
                    toVisit.add(connectedPos);
                }
            }
        }
    }

    /**
     * Gets all positions that could be connected wires (horizontal and diagonal).
     */
    protected List<Vector3i> getConnectedWirePositions(Dimension dimension, Vector3ic pos) {
        List<Vector3i> positions = new ArrayList<>();

        // Check horizontal neighbors
        for (BlockFace face : BlockFace.getHorizontalBlockFaces()) {
            Vector3i horizontalPos = new Vector3i(face.offsetPos(pos));
            positions.add(horizontalPos);

            BlockState horizontalState = dimension.getBlockState(horizontalPos);

            // Check upward diagonal
            if (!horizontalState.getBlockStateData().isSolid() || horizontalState.getBlockStateData().isTransparent()) {
                positions.add(new Vector3i(horizontalPos).add(0, 1, 0));
            }

            // Check downward diagonal
            BlockState aboveUs = dimension.getBlockState(BlockFace.UP.offsetPos(pos));
            if (!aboveUs.getBlockStateData().isSolid() || aboveUs.getBlockStateData().isTransparent()) {
                positions.add(new Vector3i(horizontalPos).add(0, -1, 0));
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
        for (BlockFace face : BlockFace.values()) {
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

            // Check strong power through solid blocks
            if (face != BlockFace.UP && neighborState.getBlockStateData().isSolid() && !neighborState.getBlockStateData().isTransparent()) {
                int strongPower = getStrongPowerIntoSolidBlock(dimension, neighborPos, face);
                maxPower = Math.max(maxPower, strongPower);
            }
        }

        return maxPower;
    }

    /**
     * Gets strong power flowing into a solid block from any direction.
     */
    protected int getStrongPowerIntoSolidBlock(Dimension dimension, Vector3ic solidPos, BlockFace excludeFace) {
        int maxPower = 0;

        for (BlockFace face : BlockFace.values()) {
            if (face == excludeFace) continue;

            Vector3ic checkPos = face.offsetPos(solidPos);
            BlockState state = dimension.getBlockState(checkPos);

            // Skip redstone wire for strong power calculation
            if (isRedstoneWire(state)) {
                continue;
            }

            Block checkBlock = new Block(state, new Position3i(checkPos, dimension));
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
        // Redstone wire only outputs horizontally
        if (face == BlockFace.UP || face == BlockFace.DOWN) {
            return 0;
        }
        return block.getPropertyValue(REDSTONE_SIGNAL);
    }

    @Override
    public int getStrongPower(Block block, BlockFace face) {
        // Redstone wire does not provide strong power
        return 0;
    }

    @Override
    public boolean isPowerSource() {
        // Redstone wire is not a power source, it only transmits power
        return false;
    }
}
