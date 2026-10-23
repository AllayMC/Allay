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
 * {@code PistonPushCalculator} builds a movement plan for one piston extension or
 * sticky retraction without changing world blocks. The plan separates movable
 * positions, blocks selected for destruction, and the original states needed by
 * execution. Extension starts next to the piston, while sticky retraction starts two
 * positions away and moves toward the base.
 *
 * <p>
 * Line discovery follows movement direction and also gathers blocks behind sticky
 * origins and perpendicular branches. The shared block limit applies to the accumulated
 * movement plan, and intersecting lines reorder previously collected positions. Sticky
 * compatibility is tag-based, with two sticky blocks requiring the same block type. A
 * failed calculation can leave partial collections, so callers execute a plan only
 * after {@code calculate} succeeds. Result getters expose unmodifiable live views whose
 * contents are cleared and rebuilt by the next calculation; position objects remain
 * shared.
 *
 * @author daoge_cmd
 */
public class PistonPushCalculator {

    /**
     * {@code MAX_PUSH_BLOCKS} limits the accumulated movable positions to twelve;
     * destruction entries are tracked separately and do not consume this movement
     * allowance.
     */
    public static final int MAX_PUSH_BLOCKS = 12;

    /**
     * Dimension queried for candidate block states.
     */
    private final Dimension dimension;
    /**
     * {@code pistonPos} retains the supplied base position without copying. Callers
     * must leave its coordinates unchanged while calculation or result consumption
     * depends on this calculator.
     */
    private final Vector3ic pistonPos;
    // pushDirection keeps the piston's outward face, while moveDirection
    // follows that face during extension and reverses it during retraction. Line
    // discovery follows movement without changing the piston axis used for branching.
    /**
     * Piston extension face supplied at construction.
     */
    private final BlockFace pushDirection;
    /**
     * Actual movement face for this extension or retraction.
     */
    private final BlockFace moveDirection;
    /**
     * Whether retraction can gather a pull plan.
     */
    private final boolean sticky;
    /**
     * Whether this plan moves outward rather than inward.
     */
    private final boolean extending;

    // toMove, toDestroy, originalStates, and visited belong to one calculation.
    // calculate clears all four before discovery, and failure retains any partial
    // entries rather than rolling the plan back.
    /**
     * {@code toMove} holds ordered source positions for movement. Its getter exposes a
     * live unmodifiable view, and {@code calculate} clears and rebuilds the same list.
     */
    private final List<Vector3ic> toMove = new ArrayList<>();
    /**
     * {@code toDestroy} holds destruction positions separately from the movement limit.
     * Its getter exposes a live unmodifiable view rebuilt by each calculation.
     */
    private final List<Vector3ic> toDestroy = new ArrayList<>();
    /**
     * {@code originalStates} retains discovered source states for movement and
     * destruction entries. Its getter exposes a live unmodifiable view, with mutable
     * position keys still shared.
     */
    private final Map<Vector3ic, BlockState> originalStates = new LinkedHashMap<>();
    /**
     * Positions already admitted to discovery bookkeeping.
     */
    private final Set<Vector3ic> visited = new HashSet<>();

    /**
     * Creates a plan calculator retaining the supplied dimension and piston position.
     * {@code pushDirection} always identifies the piston front; the derived movement
     * direction is reversed only for retraction, and callers must not mutate the
     * retained position during use.
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
        this.moveDirection = extending ? pushDirection : pushDirection.opposite();
        this.sticky = sticky;
        this.extending = extending;
    }

    /**
     * Rebuilds the movement and destruction plan from the current dimension state.
     * Non-sticky retraction succeeds with an empty plan, and an empty or replaceable
     * starting position also succeeds without movement. An immovable starting block
     * fails before line discovery.
     *
     * <p>
     * Successful line discovery expands sticky branches while the movement list can
     * grow. The result remains a proposal: no world state is reserved or changed. A
     * {@code false} result leaves any partial discoveries available through the live
     * getters, so callers must reject that plan rather than execute its surviving
     * prefix.
     */
    public boolean calculate() {
        toMove.clear();
        toDestroy.clear();
        originalStates.clear();
        visited.clear();

        if (!extending && !sticky) {
            return true;
        }

        Vector3ic startPos;
        if (extending) {
            startPos = pushDirection.offsetPos(pistonPos);
        } else {
            startPos = pushDirection.offsetPos(pistonPos, 2);
        }

        BlockState startState = dimension.getBlockState(startPos);

        if (isAirOrReplaceable(startState)) {
            return true;
        }

        if (!canMoveBlock(startState, extending)) {
            return false;
        }

        if (shouldDestroyBlock(startState)) {
            toDestroy.add(startPos);
            originalStates.put(startPos, startState);
            return true;
        }

        if (!addBlockLine(startPos, true)) {
            return false;
        }

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
     * Adds an origin, sticky trailing blocks, and the movement path ahead to the shared
     * plan. Air, replaceable blocks, and visited origins require no additional work. An
     * immovable main-line origin fails, while an immovable branch origin is ignored; an
     * immovable block ahead still blocks either kind of line.
     *
     * <p>
     * Trailing sticky blocks are gathered in reverse movement direction and appended in
     * reversed discovery order. Forward discovery can encounter an earlier line,
     * triggering list reordering instead of duplication at that encounter. The shared
     * movement limit can fail after partial additions, and this helper does not roll
     * them back.
     */
    private boolean addBlockLine(Vector3ic origin, boolean isMainLine) {
        BlockState blockState = dimension.getBlockState(origin);

        if (isAirOrReplaceable(blockState)) {
            return true;
        }

        if (visited.contains(origin)) {
            return true;
        }

        if (!canMoveBlock(blockState, extending)) {
            return isMainLine ? false : true;
        }

        if (shouldDestroyBlock(blockState)) {
            if (!toDestroy.contains(origin)) {
                toDestroy.add(origin);
                originalStates.put(origin, blockState);
            }
            return true;
        }

        if (posEquals(origin, pistonPos)) {
            return true;
        }

        // Destruction does not consume movement slots, but another movable
        // source would exceed the twelve-block plan limit shared by all lines and branches.
        if (toMove.size() >= MAX_PUSH_BLOCKS) {
            return false;
        }

        visited.add(origin);
        toMove.add(origin);
        originalStates.put(origin, blockState);

        int count = 1;
        List<Vector3ic> behindBlocks = new ArrayList<>();
        BlockFace behindDirection = moveDirection.opposite();

        while (canStickBlocks(blockState)) {
            Vector3ic behindPos = behindDirection.offsetPos(origin, count);

            BlockState behindState = dimension.getBlockState(behindPos);

            if (canStickBlocks(blockState) && canStickBlocks(behindState)) {
                if (!canStickyBlocksStickTogether(blockState, behindState)) {
                    break;
                }
            }

            if (isAirOrReplaceable(behindState) || !canMoveBlock(behindState, extending) || posEquals(behindPos, pistonPos)) {
                break;
            }

            if (shouldDestroyBlock(behindState)) {
                toDestroy.add(behindPos);
                originalStates.put(behindPos, behindState);
                break;
            }

            // Trailing entries are not in toMove yet; include them in the
            // shared movement budget before admitting another block from the sticky chain.
            if (toMove.size() + behindBlocks.size() >= MAX_PUSH_BLOCKS) {
                return false;
            }

            visited.add(behindPos);
            behindBlocks.add(behindPos);
            originalStates.put(behindPos, behindState);
            blockState = behindState;
            count++;
        }

        // Sticky trailing blocks were found outward from the origin. Reverse
        // that segment before appending it so later collision reordering receives its expected trailing order.
        Collections.reverse(behindBlocks);
        toMove.addAll(behindBlocks);

        int step = 1;
        while (true) {
            Vector3ic frontPos = moveDirection.offsetPos(origin, step);

            BlockState frontState = dimension.getBlockState(frontPos);

            if (toMove.contains(frontPos)) {
                // This line has reached an existing planned source. Reorder
                // the trailing segment instead of scanning through the same movement path again.
                reorderListAtCollision(behindBlocks.size(), toMove.indexOf(frontPos));
                return true;
            }

            if (!extending) {
                Vector3ic armPos = pushDirection.offsetPos(pistonPos);
                if (posEquals(frontPos, armPos)) {
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

            // Destruction does not consume movement slots, but another movable
            // source would exceed the twelve-block plan limit shared by all lines and branches.
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
     * Adds compatible blocks beside a sticky movement entry on axes perpendicular to
     * piston extension. The source state comes from {@code originalStates}, while
     * adjacent states are read from the live dimension. Already visited, replaceable,
     * and incompatible neighbors are skipped before line discovery.
     *
     * <p>
     * Each accepted neighbor uses {@code addBlockLine} in branch mode, allowing an
     * immovable branch origin to remain behind while obstacles farther along an
     * accepted line can reject the complete plan. Newly added movement entries are
     * later examined by the growing-list traversal in {@code calculate}.
     */
    private boolean addBranchingBlocks(Vector3ic pos) {
        BlockState blockState = originalStates.get(pos);

        for (BlockFace face : BlockFace.values()) {
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
     * Reorders the accumulated movement list when a new line intersects an existing
     * entry. The prefix before {@code collisionIndex} remains first, followed by the
     * trailing {@code behindCount} entries, then the intervening segment. The caller
     * supplies indices delimiting nonoverlapping segments within {@code toMove}.
     *
     * <p>
     * Each segment is copied before the original list is cleared, preventing backed
     * sublists from being invalidated during reconstruction. Original state mappings
     * remain keyed by source position, so reordering changes execution order without
     * replacing the associated state entries.
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
     * Returns whether the state is air or carries the replaceable block tag.
     */
    private boolean isAirOrReplaceable(BlockState state) {
        BlockType<?> type = state.getBlockType();
        return type == BlockTypes.AIR || type.hasBlockTag(BlockTags.REPLACEABLE);
    }

    /**
     * Returns whether tags and explicit arm exclusions permit movement in this
     * operation. Pulling additionally rejects {@code UNPULLABLE}; piston bases are not
     * currently rejected for an extended state by this helper.
     */
    private boolean canMoveBlock(BlockState state, boolean pushing) {
        BlockType<?> type = state.getBlockType();

        if (type.hasBlockTag(BlockTags.UNPUSHABLE)) {
            return false;
        }

        if (!pushing && type.hasBlockTag(BlockTags.UNPULLABLE)) {
            return false;
        }

        if (type == BlockTypes.PISTON || type == BlockTypes.STICKY_PISTON) {
            // TODO: Reject extended piston bases after checking their arm entity
            // or matching collision head; this branch currently adds no movement restriction.
        }

        if (type == BlockTypes.PISTON_ARM_COLLISION || type == BlockTypes.STICKY_PISTON_ARM_COLLISION) {
            return false;
        }

        return true;
    }

    /**
     * Returns whether the state carries the break-when-pushed block tag.
     */
    private boolean shouldDestroyBlock(BlockState state) {
        return state.getBlockType().hasBlockTag(BlockTags.BREAK_WHEN_PUSHED);
    }

    /**
     * Returns whether the state's type carries the sticky-block connection tag.
     */
    private boolean canStickBlocks(BlockState state) {
        return state.getBlockType().hasBlockTag(BlockTags.CAN_STICK_BLOCKS);
    }

    /**
     * Returns whether both positions have identical integer coordinates, ignoring
     * dimension and runtime class. Coordinate comparison avoids the class-sensitive
     * object equality of {@code Vector3i} when one position is a dimension-bearing
     * wrapper.
     */
    private boolean posEquals(Vector3ic a, Vector3ic b) {
        return a.x() == b.x() && a.y() == b.y() && a.z() == b.z();
    }

    /**
     * Returns whether two states participate in this calculator's sticky connection
     * rule. Two states without the sticky tag do not attach through this mechanism. One
     * tagged state can carry an untagged state, while two tagged states attach only
     * when their block types are identical.
     *
     * <p>
     * This predicate describes adhesion rather than permission to move. Line discovery
     * separately applies {@code canMoveBlock}, so a compatible sticky relationship does
     * not bypass unpushable tags, pull restrictions, or collision-arm exclusions.
     * Different tagged types therefore remain separate even when both can adhere to
     * ordinary blocks.
     */
    private boolean canStickyBlocksStickTogether(BlockState state1, BlockState state2) {
        boolean state1Sticky = canStickBlocks(state1);
        boolean state2Sticky = canStickBlocks(state2);

        if (!state1Sticky && !state2Sticky) {
            return false;
        }

        if (!state1Sticky || !state2Sticky) {
            return true;
        }

        return state1.getBlockType() == state2.getBlockType();
    }

    /**
     * Returns an unmodifiable live view of movement source positions. The next
     * {@code calculate} call clears and rebuilds it, failed calculations may leave
     * partial entries, and callers must not mutate the shared position objects.
     */
    public List<Vector3ic> getBlocksToMove() {
        return Collections.unmodifiableList(toMove);
    }

    /**
     * Returns an unmodifiable live view of positions selected for destruction. The next
     * {@code calculate} call rebuilds it, failure may leave a partial plan, and
     * position objects remain shared with internal bookkeeping.
     */
    public List<Vector3ic> getBlocksToDestroy() {
        return Collections.unmodifiableList(toDestroy);
    }

    /**
     * Returns an unmodifiable live view of discovered source states. The next
     * calculation rebuilds the map, and callers must not mutate shared position keys
     * because their hashes determine later state lookups.
     */
    public Map<Vector3ic, BlockState> getOriginalStates() {
        return Collections.unmodifiableMap(originalStates);
    }
}
