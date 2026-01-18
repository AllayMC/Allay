package org.allaymc.server.block.component.piston;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.util.*;

/**
 * Calculator for piston push/pull operations.
 * <p>
 * This class implements the algorithm to calculate which blocks need to be
 * moved when a piston extends or retracts, following vanilla Minecraft rules.
 *
 * @author daoge_cmd
 */
public class PistonPushCalculator {

    /**
     * Maximum number of blocks a piston can push.
     */
    public static final int MAX_PUSH_BLOCKS = 12;

    private final Dimension dimension;
    private final Vector3ic pistonPos;
    private final BlockFace pushDirection;
    private final boolean sticky;
    private final boolean extending;

    private final List<Vector3ic> toMove = new ArrayList<>();
    private final List<Vector3ic> toDestroy = new ArrayList<>();
    private final Map<Vector3ic, BlockState> originalStates = new LinkedHashMap<>();
    private final Set<Vector3ic> visited = new HashSet<>();

    /**
     * Create a new piston push calculator.
     *
     * @param dimension     The dimension to operate in
     * @param pistonPos     The position of the piston block
     * @param pushDirection The direction the piston is pushing
     * @param sticky        Whether the piston is sticky
     * @param extending     Whether the piston is extending or retracting
     */
    public PistonPushCalculator(
            Dimension dimension,
            Vector3ic pistonPos,
            BlockFace pushDirection,
            boolean sticky,
            boolean extending
    ) {
        this.dimension = dimension;
        this.pistonPos = pistonPos;
        this.pushDirection = pushDirection;
        this.sticky = sticky;
        this.extending = extending;
    }

    /**
     * Calculate the blocks to be moved by the piston.
     *
     * @return true if the piston can push/pull, false otherwise
     */
    public boolean calculate() {
        toMove.clear();
        toDestroy.clear();
        originalStates.clear();
        visited.clear();

        // For non-sticky pistons retracting, there's nothing to pull
        if (!extending && !sticky) {
            return true;
        }

        // Get the first block to check
        Vector3ic startPos;
        if (extending) {
            startPos = pushDirection.offsetPos(pistonPos);
        } else {
            // For sticky piston retracting, check the block 2 positions away
            startPos = pushDirection.offsetPos(pistonPos, 2);
        }

        BlockState startState = dimension.getBlockState(startPos);

        // If the start position is air or replaceable, nothing to push
        if (isAirOrReplaceable(startState)) {
            return true;
        }

        // Check if the block can be pushed/pulled
        if (!canMoveBlock(startState, extending)) {
            return false;
        }

        // If the block should be destroyed when moved
        if (shouldDestroyBlock(startState)) {
            toDestroy.add(startPos);
            originalStates.put(startPos, startState);
            return true;
        }

        // Start the recursive calculation
        if (!addBlockLine(startPos, true)) {
            return false;
        }

        // For blocks in toMove, check for sticky block connections
        for (int i = 0; i < toMove.size(); i++) {
            Vector3ic pos = toMove.get(i);
            BlockState state = originalStates.get(pos);
            if (canStickBlocks(state)) {
                if (!addBranchingBlocks(pos)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Add a line of blocks starting from the given position.
     *
     * @param origin        The starting position
     * @param isMainLine    Whether this is the main line (in push direction)
     * @return true if successful, false if push is blocked
     */
    private boolean addBlockLine(Vector3ic origin, boolean isMainLine) {
        BlockState blockState = dimension.getBlockState(origin);

        // Skip air or replaceable blocks
        if (isAirOrReplaceable(blockState)) {
            return true;
        }

        // If we've already processed this position
        if (visited.contains(origin)) {
            return true;
        }

        // Check if the block can be moved
        if (!canMoveBlock(blockState, extending)) {
            return isMainLine ? false : true;
        }

        // Check if the block should be destroyed
        if (shouldDestroyBlock(blockState)) {
            if (!toDestroy.contains(origin)) {
                toDestroy.add(origin);
                originalStates.put(origin, blockState);
            }
            return true;
        }

        // Check if we've reached the piston itself
        if (origin.equals(pistonPos)) {
            return true;
        }

        // Check block limit
        if (toMove.size() >= MAX_PUSH_BLOCKS) {
            return false;
        }

        visited.add(origin);
        toMove.add(origin);
        originalStates.put(origin, blockState);

        // Check blocks behind this one (blocks that might be pulled along by sticky blocks)
        int count = 1;
        List<Vector3ic> behindBlocks = new ArrayList<>();
        BlockFace oppositeDirection = pushDirection.opposite();

        while (canStickBlocks(blockState)) {
            Vector3ic behindPos = oppositeDirection.offsetPos(origin, count);

            BlockState behindState = dimension.getBlockState(behindPos);

            // Check for sticky block incompatibility (slime and honey don't stick to each other)
            if (canStickBlocks(blockState) && canStickBlocks(behindState)) {
                if (!canStickyBlocksStickTogether(blockState, behindState)) {
                    break;
                }
            }

            if (isAirOrReplaceable(behindState) || !canMoveBlock(behindState, extending) || behindPos.equals(pistonPos)) {
                break;
            }

            if (shouldDestroyBlock(behindState)) {
                toDestroy.add(behindPos);
                originalStates.put(behindPos, behindState);
                break;
            }

            if (toMove.size() + behindBlocks.size() >= MAX_PUSH_BLOCKS) {
                return false;
            }

            visited.add(behindPos);
            behindBlocks.add(behindPos);
            originalStates.put(behindPos, behindState);
            blockState = behindState;
            count++;
        }

        // Add behind blocks in reverse order
        Collections.reverse(behindBlocks);
        toMove.addAll(behindBlocks);

        // Check blocks in front (in push direction)
        int step = 1;
        while (true) {
            Vector3ic frontPos = pushDirection.offsetPos(origin, step);

            BlockState frontState = dimension.getBlockState(frontPos);

            // If we've already processed this position (collision with another push line)
            if (toMove.contains(frontPos)) {
                reorderListAtCollision(behindBlocks.size(), toMove.indexOf(frontPos));
                return true;
            }

            // Check for the piston arm position (when retracting)
            if (!extending) {
                Vector3ic armPos = pushDirection.offsetPos(pistonPos);
                if (frontPos.equals(armPos)) {
                    return true;
                }
            }

            if (isAirOrReplaceable(frontState)) {
                return true;
            }

            if (!canMoveBlock(frontState, extending)) {
                return false;
            }

            if (shouldDestroyBlock(frontState)) {
                if (!toDestroy.contains(frontPos)) {
                    toDestroy.add(frontPos);
                    originalStates.put(frontPos, frontState);
                }
                return true;
            }

            if (toMove.size() >= MAX_PUSH_BLOCKS) {
                return false;
            }

            visited.add(frontPos);
            toMove.add(frontPos);
            originalStates.put(frontPos, frontState);
            step++;
        }
    }

    /**
     * Add blocks connected to sticky blocks (perpendicular to push direction).
     */
    private boolean addBranchingBlocks(Vector3ic pos) {
        BlockState blockState = originalStates.get(pos);

        for (BlockFace face : BlockFace.values()) {
            // Skip the push direction and its opposite
            if (face == pushDirection || face == pushDirection.opposite()) {
                continue;
            }

            Vector3ic adjacentPos = face.offsetPos(pos);
            if (visited.contains(adjacentPos)) {
                continue;
            }

            BlockState adjacentState = dimension.getBlockState(adjacentPos);

            if (isAirOrReplaceable(adjacentState)) {
                continue;
            }

            // Check if these blocks can stick together
            if (!canStickyBlocksStickTogether(blockState, adjacentState)) {
                continue;
            }

            if (!addBlockLine(adjacentPos, false)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Reorder the toMove list when a collision is detected.
     */
    private void reorderListAtCollision(int behindCount, int collisionIndex) {
        List<Vector3ic> before = new ArrayList<>(toMove.subList(0, collisionIndex));
        List<Vector3ic> behind = new ArrayList<>(toMove.subList(toMove.size() - behindCount, toMove.size()));
        List<Vector3ic> middle = new ArrayList<>(toMove.subList(collisionIndex, toMove.size() - behindCount));

        toMove.clear();
        toMove.addAll(before);
        toMove.addAll(behind);
        toMove.addAll(middle);
    }

    /**
     * Check if a block state is air or replaceable.
     */
    private boolean isAirOrReplaceable(BlockState state) {
        BlockType<?> type = state.getBlockType();
        return type == BlockTypes.AIR || type.hasBlockTag(BlockTags.REPLACEABLE);
    }

    /**
     * Check if a block can be moved by the piston.
     */
    private boolean canMoveBlock(BlockState state, boolean pushing) {
        BlockType<?> type = state.getBlockType();

        // Check unpushable tag
        if (type.hasBlockTag(BlockTags.UNPUSHABLE)) {
            return false;
        }

        // When pulling, also check unpullable tag
        if (!pushing && type.hasBlockTag(BlockTags.UNPULLABLE)) {
            return false;
        }

        // Pistons cannot push extended pistons
        if (type == BlockTypes.PISTON || type == BlockTypes.STICKY_PISTON) {
            // TODO: Check if piston is extended using block property
            // For now, assume pistons can be pushed when not extended
        }

        // Piston arm collision blocks cannot be pushed
        if (type == BlockTypes.PISTON_ARM_COLLISION || type == BlockTypes.STICKY_PISTON_ARM_COLLISION) {
            return false;
        }

        return true;
    }

    /**
     * Check if a block should be destroyed when pushed.
     */
    private boolean shouldDestroyBlock(BlockState state) {
        return state.getBlockType().hasBlockTag(BlockTags.BREAK_WHEN_PUSHED);
    }

    /**
     * Check if a block can stick to other blocks (slime/honey block).
     */
    private boolean canStickBlocks(BlockState state) {
        return state.getBlockType().hasBlockTag(BlockTags.CAN_STICK_BLOCKS);
    }

    /**
     * Check if two sticky blocks can stick together.
     * Slime and honey blocks don't stick to each other.
     */
    private boolean canStickyBlocksStickTogether(BlockState state1, BlockState state2) {
        boolean state1Sticky = canStickBlocks(state1);
        boolean state2Sticky = canStickBlocks(state2);

        // If neither is sticky, they don't stick together via this mechanism
        if (!state1Sticky && !state2Sticky) {
            return false;
        }

        // If only one is sticky, the other block gets pulled along
        if (!state1Sticky || !state2Sticky) {
            return true;
        }

        // If both are sticky, they only stick if they're the same type
        // (slime doesn't stick to honey and vice versa)
        return state1.getBlockType() == state2.getBlockType();
    }

    /**
     * Get the list of blocks to be moved.
     */
    public List<Vector3ic> getBlocksToMove() {
        return Collections.unmodifiableList(toMove);
    }

    /**
     * Get the list of blocks to be destroyed.
     */
    public List<Vector3ic> getBlocksToDestroy() {
        return Collections.unmodifiableList(toDestroy);
    }

    /**
     * Get the original block states of all affected blocks.
     */
    public Map<Vector3ic, BlockState> getOriginalStates() {
        return Collections.unmodifiableMap(originalStates);
    }
}
