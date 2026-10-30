package org.allaymc.server.block.component.piston;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityMovingBlock;
import org.allaymc.api.blockentity.interfaces.BlockEntityPistonArm;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockPistonEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;

/**
 * {@code BlockPistonBaseComponentImpl} coordinates power checks, cancellable movement
 * planning, and block placement for normal and sticky pistons. A piston arm block
 * entity tracks cached input power and animation state. Even states represent completed
 * retraction or extension, while odd states prevent this component from starting
 * another transition during movement.
 *
 * <p>
 * Extension and sticky pull planning preserve source states separately from destination
 * blocks. Execution prepares the arm's initial client state before creating
 * moving-block placeholders, saves block entity data before clearing sources, and then
 * starts arm-driven animation. The arm entity finalizes those placeholders and restores
 * saved data during its own ticks. Individual dimension writes and block breaks can be
 * refused without rolling back earlier work, so movement execution is not
 * transactional. Failed or cancelled extension receives another check while power
 * remains present; cancelled retraction does not receive the same automatic retry path.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockPistonBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code ANIMATION_DURATION} requests a piston state check two game ticks after
     * movement starts; the arm entity itself advances and finalizes animation.
     */
    protected static final Duration ANIMATION_DURATION = Duration.ofMillis(100);

    /**
     * Creates the piston component for the supplied registered block type.
     */
    public BlockPistonBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a piston with player-derived facing and initializes its arm entity only
     * after accepted placement. A {@code null} {@code placementInfo} preserves the
     * supplied facing. The component then assigns sticky identity, decoded direction,
     * and current input power to a present arm entity.
     *
     * <p>
     * A powered position requests a check for the next game tick, allowing the normal
     * scheduled path to start extension. Placement success is returned even when the
     * arm entity is absent; an unexpected non-arm entity can fail the unchecked cast
     * used during this initialization.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            int facing = getPlacementFacing(placeBlockPos, placementInfo);
            blockState = blockState.setPropertyValue(FACING_DIRECTION, facing);
        }

        boolean result = dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                blockState, placementInfo
        );

        if (result) {
            var pistonArm = (BlockEntityPistonArm) dimension.getBlockEntity(placeBlockPos);
            if (pistonArm != null) {
                pistonArm.setSticky(isSticky());
                pistonArm.setFacing(getPistonFace(blockState));

                Block block = new Block(dimension, placeBlockPos);
                boolean isPowered = hasRedstonePower(block);
                pistonArm.setPowered(isPowered);

                if (isPowered) {
                    dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(placeBlockPos, Duration.ofMillis(50));
                }
            }
        }

        return result;
    }

    /**
     * Requests a piston state check one game tick after inherited neighbor handling.
     * The position-based scheduler can replace a pending entry, and the later callback
     * samples power rather than retaining this notification's input state.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        block.scheduleUpdateInDelay(Duration.ofMillis(50));
    }

    /**
     * Checks cached power, animation state, and the presence of an extended collision
     * arm before choosing movement. A missing arm entity returns without action.
     * Completed even animation states allow a changed input to update the entity's
     * cached power before extension or retraction is attempted.
     *
     * <p>
     * A failed or cancelled extension schedules another next-tick check. The later
     * fallback also attempts extension when power is unchanged but the piston remains
     * retracted, allowing cached input to survive retries. Retraction's result is
     * ignored after cached power is cleared, so cancelled retraction has no
     * corresponding unchanged-power retry here. Odd animation states start neither path
     * and rely on later scheduled or arm-driven notifications.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        var blockState = block.getBlockState();

        var pistonArmBlockEntity = dimension.getBlockEntity(pos);
        if (!(pistonArmBlockEntity instanceof BlockEntityPistonArm pistonArm)) {
            return;
        }

        boolean isPowered = hasRedstonePower(block);

        if (pistonArm.getState() % 2 == 0 && pistonArm.isPowered() != isPowered) {
            pistonArm.setPowered(isPowered);

            boolean isExtended = isExtended(dimension, pos, blockState);

            if (isPowered && !isExtended) {
                // Cache updates precede the attempt, so a failed extension needs
                // another check even without a new input edge to trigger the normal transition path.
                if (!extend(block)) {
                    block.scheduleUpdateInDelay(Duration.ofMillis(50));
                }
            } else if (!isPowered && isExtended) {
                retract(block);
            }
            return;
        }

        if (isPowered && !isExtended(dimension, pos, blockState) && pistonArm.getState() % 2 == 0) {
            // Cache updates precede the attempt, so a failed extension needs
            // another check even without a new input edge to trigger the normal transition path.
            if (!extend(block)) {
                block.scheduleUpdateInDelay(Duration.ofMillis(50));
            }
        }
    }

    /**
     * Calculates an outward movement plan, dispatches {@code BlockPistonEvent}, and
     * executes the plan unless cancellation is immediately observed. Calculation
     * failure or a cancelled flag read after dispatch returns produces {@code false}
     * before movement execution. Copies of the calculator's collection containers are
     * passed to the event and reused by execution.
     *
     * <p>
     * Synchronous listeners finish before that cancellation check and can edit those
     * retained collections. Asynchronous handlers are only submitted; late cancellation
     * can miss the check, and late plan edits can race with execution. A completed
     * execution call plays the push sound and returns {@code true}, without verifying
     * every underlying block write or break. The result signals that execution was
     * invoked, rather than that all mutations succeeded atomically.
     */
    protected boolean extend(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        var blockState = block.getBlockState();
        BlockFace facing = getPistonFace(blockState);

        PistonPushCalculator calculator = new PistonPushCalculator(
                dimension, pos, facing, isSticky(), true
        );

        if (!calculator.calculate()) {
            return false;
        }

        List<Vector3ic> blocksToMove = new ArrayList<>(calculator.getBlocksToMove());
        List<Vector3ic> blocksToDestroy = new ArrayList<>(calculator.getBlocksToDestroy());
        Map<Vector3ic, BlockState> originalStates = new HashMap<>(calculator.getOriginalStates());

        BlockPistonEvent event = new BlockPistonEvent(
                block, facing, blocksToMove, blocksToDestroy, originalStates, true, isSticky()
        );

        if (Server.getInstance().getEventBus().callEvent(event).isCancelled()) {
            return false;
        }

        executePush(dimension, pos, facing, blocksToMove, blocksToDestroy, originalStates);

        dimension.addSound(pos.x() + 0.5, pos.y() + 0.5, pos.z() + 0.5, SimpleSound.PISTON_PUSH);

        return true;
    }

    /**
     * Dispatches a retraction proposal and executes it unless cancellation is
     * immediately observed. Sticky pistons attempt pull planning first, but a failed
     * pull leaves empty movement collections and does not prevent the piston itself
     * from retracting. The event receives those mutable collections later consumed by
     * execution.
     *
     * <p>
     * Synchronous listeners finish before the caller reads cancellation after dispatch.
     * Asynchronous handlers are not awaited, so late cancellation can miss that check
     * and late collection edits can race with execution. An observed cancellation
     * returns {@code false}; otherwise the method plays the pull sound and returns
     * {@code true} after invoking execution without aggregating individual block-update
     * results. Its scheduled caller does not retry a cancelled retraction merely
     * because input remains absent.
     */
    protected boolean retract(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        var blockState = block.getBlockState();
        BlockFace facing = getPistonFace(blockState);

        List<Vector3ic> blocksToMove = new ArrayList<>();
        List<Vector3ic> blocksToDestroy = new ArrayList<>();
        Map<Vector3ic, BlockState> originalStates = new HashMap<>();

        if (isSticky()) {
            PistonPushCalculator calculator = new PistonPushCalculator(
                    dimension, pos, facing, true, false
            );

            if (calculator.calculate()) {
                blocksToMove.addAll(calculator.getBlocksToMove());
                blocksToDestroy.addAll(calculator.getBlocksToDestroy());
                originalStates.putAll(calculator.getOriginalStates());
            }
        }

        BlockPistonEvent event = new BlockPistonEvent(
                block, facing, blocksToMove, blocksToDestroy, originalStates, false, isSticky()
        );

        if (Server.getInstance().getEventBus().callEvent(event).isCancelled()) {
            return false;
        }

        executeRetract(dimension, pos, facing, blocksToMove, blocksToDestroy, originalStates);

        dimension.addSound(pos.x() + 0.5, pos.y() + 0.5, pos.z() + 0.5, SimpleSound.PISTON_PULL);

        return true;
    }

    /**
     * Executes an extension plan using original states keyed by source positions. A
     * present arm entity first publishes its prepared extension state, before
     * moving-block placeholders are created. Destruction requests run next, followed by
     * saving source block entity data and disabling moved containers' break drops
     * before sources are cleared.
     *
     * <p>
     * Sources are cleared in reverse list order, and moving placeholders are then
     * placed in list order at outward destinations. A missing original state skips
     * destination initialization even though its source may already have been cleared.
     * Saved entity data is retained on each moving entity for arm-driven restoration.
     * The collision head is placed before animation starts, and a later state check is
     * scheduled. Missing entities and refused writes do not trigger rollback of earlier
     * changes.
     */
    protected void executePush(
            Dimension dimension,
            Vector3ic pistonPos,
            BlockFace facing,
            List<Vector3ic> blocksToMove,
            List<Vector3ic> blocksToDestroy,
            Map<Vector3ic, BlockState> originalStates
    ) {
        var pistonArmBlockEntity = dimension.getBlockEntity(pistonPos);
        if (pistonArmBlockEntity instanceof BlockEntityPistonArm pistonArm) {
            // Publish the arm's initial progress before any moving placeholders
            // are sent, so clients receive the motion owner before its attached block representations.
            pistonArm.preExtending(blocksToMove, originalStates);
        }

        for (Vector3ic pos : blocksToDestroy) {
            BlockState state = originalStates.get(pos);
            if (state != null) {
                dimension.breakBlock(pos, null, null);
            }
        }

        // Capture entity data before clearing sources, then suppress container
        // break drops so moving inventory is restored later instead of also spilling at its old position.
        Map<Vector3ic, org.cloudburstmc.nbt.NbtMap> blockEntityNBTs = new HashMap<>();
        for (Vector3ic pos : blocksToMove) {
            var blockEntity = dimension.getBlockEntity(pos);
            if (blockEntity != null) {
                blockEntityNBTs.put(pos, blockEntity.saveCleanNBT());
                if (blockEntity instanceof BlockEntityContainerHolderComponent containerHolder) {
                    containerHolder.setDropItemOnBreak(false);
                }
            }
        }

        for (int i = blocksToMove.size() - 1; i >= 0; i--) {
            Vector3ic pos = blocksToMove.get(i);
            dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
        }

        for (int i = 0; i < blocksToMove.size(); i++) {
            Vector3ic oldPos = blocksToMove.get(i);
            Vector3ic newPos = facing.offsetPos(oldPos);
            BlockState state = originalStates.get(oldPos);

            if (state != null) {
                dimension.setBlockState(newPos, BlockTypes.MOVING_BLOCK.getDefaultState());
                var movingBlockEntity = dimension.getBlockEntity(newPos);
                if (movingBlockEntity instanceof BlockEntityMovingBlock movingBlock) {
                    movingBlock.setMovingBlockState(state);
                    movingBlock.setPistonPos(new Vector3i(pistonPos));
                    movingBlock.setExpanding(true);
                    movingBlock.setMovingBlockEntityNBT(blockEntityNBTs.get(oldPos));
                }
            }
        }

        Vector3ic armPos = facing.offsetPos(pistonPos);
        BlockType<?> armType = isSticky()
                ? BlockTypes.STICKY_PISTON_ARM_COLLISION
                : BlockTypes.PISTON_ARM_COLLISION;
        // The head shares the base's stored encoding, including horizontal
        // reversal; copying the decoded extension ordinal would point horizontal collision heads incorrectly.
        int armFacing = dimension.getBlockState(pistonPos).getPropertyValue(FACING_DIRECTION);
        dimension.setBlockState(armPos, armType.getDefaultState()
                .setPropertyValue(FACING_DIRECTION, armFacing));

        if (pistonArmBlockEntity instanceof BlockEntityPistonArm pistonArm) {
            pistonArm.startMoving();
        }

        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pistonPos, ANIMATION_DURATION);
    }

    /**
     * Executes a retraction plan after publishing prepared arm state when an arm entity
     * exists. Destruction entries are processed before sticky movement. A sticky plan
     * saves source block entity data, disables moved-container break drops, clears
     * sources, and creates inward moving placeholders carrying each original state and
     * saved data.
     *
     * <p>
     * A non-sticky or empty plan removes the collision head directly. Sticky movement
     * leaves final head cleanup to the arm entity's animation completion. Animation
     * starts only after placeholder initialization, followed by a delayed state check.
     * Individual write and break results are ignored, and a missing original state can
     * skip a destination after its source was cleared; no rollback restores partial
     * work.
     */
    protected void executeRetract(
            Dimension dimension,
            Vector3ic pistonPos,
            BlockFace facing,
            List<Vector3ic> blocksToMove,
            List<Vector3ic> blocksToDestroy,
            Map<Vector3ic, BlockState> originalStates
    ) {
        var pistonArmBlockEntity = dimension.getBlockEntity(pistonPos);
        if (pistonArmBlockEntity instanceof BlockEntityPistonArm pistonArm) {
            // Publish the arm's initial retraction progress before placeholders
            // appear, preserving the same client ordering as outward movement and its attached blocks.
            pistonArm.preRetracting(blocksToMove, originalStates);
        }

        for (Vector3ic pos : blocksToDestroy) {
            BlockState state = originalStates.get(pos);
            if (state != null) {
                dimension.breakBlock(pos, null, null);
            }
        }

        if (isSticky() && !blocksToMove.isEmpty()) {
            // Capture entity data before clearing sources, then suppress container
            // break drops so moving inventory is restored later instead of also spilling at its old position.
            Map<Vector3ic, org.cloudburstmc.nbt.NbtMap> blockEntityNBTs = new HashMap<>();
            for (Vector3ic pos : blocksToMove) {
                var blockEntity = dimension.getBlockEntity(pos);
                if (blockEntity != null) {
                    blockEntityNBTs.put(pos, blockEntity.saveCleanNBT());
                    if (blockEntity instanceof BlockEntityContainerHolderComponent containerHolder) {
                        containerHolder.setDropItemOnBreak(false);
                    }
                }
            }

            for (Vector3ic pos : blocksToMove) {
                dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
            }

            BlockFace pullDirection = facing.opposite();
            for (Vector3ic oldPos : blocksToMove) {
                Vector3ic newPos = pullDirection.offsetPos(oldPos);
                BlockState state = originalStates.get(oldPos);
                if (state != null) {
                    dimension.setBlockState(newPos, BlockTypes.MOVING_BLOCK.getDefaultState());
                    var movingBlockEntity = dimension.getBlockEntity(newPos);
                    if (movingBlockEntity instanceof BlockEntityMovingBlock movingBlock) {
                        movingBlock.setMovingBlockState(state);
                        movingBlock.setPistonPos(new Vector3i(pistonPos));
                        movingBlock.setExpanding(false);
                        movingBlock.setMovingBlockEntityNBT(blockEntityNBTs.get(oldPos));
                    }
                }
            }
        } else {
            Vector3ic armPos = facing.offsetPos(pistonPos);
            dimension.setBlockState(armPos, BlockTypes.AIR.getDefaultState());
        }

        if (pistonArmBlockEntity instanceof BlockEntityPistonArm pistonArm) {
            pistonArm.startMoving();
        }

        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pistonPos, ANIMATION_DURATION);
    }

    /**
     * Returns whether weak or strong input reaches the piston while excluding its
     * extension face. The received-power helpers sample the dimension around the base,
     * preventing front-side input from directly triggering this check.
     */
    protected boolean hasRedstonePower(Block block) {
        BlockFace pistonFace = getPistonFace(block.getBlockState());

        return block.getWeakPower(pistonFace) > 0 || block.getStrongPower(pistonFace) > 0;
    }

    /**
     * Returns whether either collision-arm type occupies the position in front of the
     * piston. This check uses the base facing to locate that position but does not
     * verify the arm's facing or ownership.
     */
    protected boolean isExtended(Dimension dimension, Vector3ic pistonPos, BlockState pistonState) {
        BlockFace facing = getPistonFace(pistonState);
        Vector3ic armPos = facing.offsetPos(pistonPos);
        BlockState armState = dimension.getBlockState(armPos);
        BlockType<?> armType = armState.getBlockType();

        return armType == BlockTypes.PISTON_ARM_COLLISION ||
               armType == BlockTypes.STICKY_PISTON_ARM_COLLISION;
    }

    /**
     * Returns the physical extension face decoded from {@code FACING_DIRECTION}.
     * Horizontal property values are reversed, while vertical values already identify
     * the extension face; callers supply a valid piston state.
     */
    protected BlockFace getPistonFace(BlockState blockState) {
        int facing = blockState.getPropertyValue(FACING_DIRECTION);
        BlockFace face = BlockFace.fromIndex(facing);
        // Horizontal metadata identifies the opposite of extension, while
        // vertical metadata already names the physical face used for motion and head placement.
        return face.isHorizontal() ? face.opposite() : face;
    }

    /**
     * Selects the stored piston facing from player proximity, eye height, and
     * horizontal direction. Distances are measured from the destination block center.
     * When the player is within two horizontal blocks, an eye more than two blocks
     * above selects upward extension, and an eye below the center selects downward
     * extension.
     *
     * <p>
     * Other cases store the player's horizontal facing directly. {@code getPistonFace}
     * later reverses that stored horizontal code to obtain physical extension, while
     * vertical codes retain their direction. Player interaction information and its
     * player must be present for this calculation.
     */
    protected int getPlacementFacing(Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var player = placementInfo.player();
        var playerPos = player.getLocation();

        // Use the destination block center for proximity and eye-height decisions.
        double dx = playerPos.x() - (placeBlockPos.x() + 0.5);
        double dz = playerPos.z() - (placeBlockPos.z() + 0.5);
        double horizontalDist = Math.sqrt(dx * dx + dz * dz);

        // Only nearby players can select vertical extension from their eye height.
        if (horizontalDist < 2.0) {
            double dy = playerPos.y() + player.getEyeHeight() - (placeBlockPos.y() + 0.5);

            // Upward placement requires greater eye clearance than the downward case.
            if (dy > 2.0) {
                return BlockFace.UP.ordinal();
            } else if (dy < 0) {
                return BlockFace.DOWN.ordinal();
            }
        }

        BlockFace horizontalFace = player.getHorizontalFace();
        return horizontalFace.ordinal();
    }

    /**
     * Runs inherited break handling and requests removal of a matching collision arm
     * for an entity-originated base break. A {@code null} {@code entity} stops
     * cascading, including the base break requested by an arm callback.
     *
     * <p>
     * The candidate in front must have a collision-arm type and the same decoded facing
     * as the base. Its break request passes a {@code null} entity so the arm cannot
     * cascade back to this base. Refusal of that request is ignored, so this callback
     * does not guarantee both blocks disappear together.
     */
    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        super.onBreak(block, usedItem, entity);

        // Arm-to-base cascades pass no entity. Stop this reciprocal path before
        // requesting another arm break from the same pair of callbacks.
        if (entity == null) {
            return;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();
        var blockState = block.getBlockState();
        BlockFace facing = getPistonFace(blockState);
        Vector3ic armPos = facing.offsetPos(pos);

        var armState = dimension.getBlockState(armPos);
        if (armState.getBlockType() == BlockTypes.PISTON_ARM_COLLISION ||
            armState.getBlockType() == BlockTypes.STICKY_PISTON_ARM_COLLISION) {
            int armFacing = armState.getPropertyValue(FACING_DIRECTION);
            BlockFace armFace = BlockFace.fromIndex(armFacing);
            if (armFace.isHorizontal()) {
                armFace = armFace.opposite();
            }
            if (armFace == facing) {
                dimension.breakBlock(armPos, null, null);
            }
        }
    }

    /**
     * Returns {@code false} so the base piston implementation omits sticky pull
     * planning.
     */
    protected boolean isSticky() {
        return false;
    }
}
