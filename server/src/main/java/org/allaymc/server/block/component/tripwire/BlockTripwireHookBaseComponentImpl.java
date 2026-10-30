package org.allaymc.server.block.component.tripwire;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * {@code BlockTripwireHookBaseComponentImpl} evaluates horizontal tripwire lines and
 * supplies redstone output from their hook endpoints. A valid connection needs a facing
 * hook at offset two through forty-one and tripwire in the intervening positions.
 * Powered, armed segments contribute output only while that complete connection remains
 * valid.
 *
 * <p>
 * Calculation scans and retains segment snapshots before updating the opposite
 * endpoint, then the current endpoint, then changed wire attachment bits.
 * State-difference guards limit repeated writes when placement callbacks reevaluate the
 * same line. Each world write can still fail independently, and the sequence provides
 * no rollback. Removal disables connection from the old endpoint without rewriting that
 * removed hook. The optional explicit segment slot can substitute a snapshot for
 * specialized reevaluation, while ordinary scheduled checks scan the live line and
 * request explicit circuit notifications at the current hook.
 *
 * @author daoge_cmd
 */
public class BlockTripwireHookBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code MAX_DISTANCE} is the exclusive hook-search bound; scans inspect offsets
     * one through forty-one, so the farthest accepted endpoint is forty-one blocks
     * away.
     */
    private static final int MAX_DISTANCE = 42;

    /**
     * Creates the tripwire hook component for the supplied registered block type.
     */
    public BlockTripwireHookBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a player-oriented hook only against a full horizontal support face.
     * Vertical clicked faces return {@code false}; {@code null} {@code placementInfo}
     * preserves the supplied state and skips face and support validation.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        BlockFace clickedFace = placementInfo.blockFace();

        if (!clickedFace.isHorizontal()) {
            return false;
        }

        BlockFace attachedFace = clickedFace.opposite();
        Vector3ic attachedPos = attachedFace.offsetPos(placeBlockPos);
        BlockState attachedState = dimension.getBlockState(attachedPos);
        if (!attachedState.getBlockStateData().collisionShape().isFull(clickedFace)) {
            return false;
        }

        blockState = blockState.setPropertyValue(DIRECTION_4, clickedFace.getHorizontalIndex());
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Recalculates the connection after inherited placement handling finishes using the
     * new hook state. This path scans the live line and does not request the extra
     * explicit current-hook neighborhood notifications controlled by
     * {@code updateAround}.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        Block newBlock = new Block(newBlockState, oldBlock.getPosition());
        calculateState(newBlock, false, false, -1, null);
    }

    /**
     * Checks the hook's support after inherited neighbor handling. An invalid facing
     * returns immediately; loss of the full attachment surface asks the hook to break,
     * while other neighbor notifications do not recalculate the line here.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        BlockFace hookFacing = getHookFacing(block);
        if (hookFacing == null) {
            return;
        }

        BlockFace attachedFace = hookFacing.opposite();
        if (face == attachedFace) {
            if (!neighbor.getBlockStateData().collisionShape().isFull(hookFacing)) {
                block.breakBlock();
            }
        }
    }

    /**
     * Detaches an active or attached old hook when another block type replaces it.
     * Same-type replacements return early, allowing property-state writes to run their
     * placement callbacks without being treated as hook removal.
     *
     * <p>
     * A removed endpoint asks {@code calculateState} to detach the opposite hook
     * without rewriting this position. If the old hook was powered, its direct and
     * support-side neighborhoods are also notified. Individual state writes and break
     * consequences are not combined into an atomic line update.
     */
    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);

        // Connection calculation writes another hook state through setBlockState.
        // Treating that replacement as removal would immediately detach the line being established.
        if (newBlockState.getBlockType() == BlockTypes.TRIPWIRE_HOOK) {
            return;
        }

        boolean attached = oldBlock.getPropertyValue(ATTACHED_BIT);
        boolean powered = oldBlock.getPropertyValue(POWERED_BIT);

        if (attached || powered) {
            calculateState(oldBlock, true, false, -1, null);
        }

        if (powered) {
            Dimension dimension = oldBlock.getDimension();
            dimension.updateAround(oldBlock.getPosition());

            BlockFace hookFacing = getHookFacing(oldBlock);
            if (hookFacing != null) {
                dimension.updateAround(hookFacing.opposite().offsetPos(oldBlock.getPosition()));
            }
        }
    }

    /**
     * Recalculates the live connection and requests extra explicit notifications around
     * a changed current hook. The callback uses no replacement segment snapshot, so it
     * observes whichever blocks occupy the line at execution.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        calculateState(block, false, true, -1, null);
    }

    /**
     * Returns maximum weak power on every queried face when the hook snapshot is
     * powered. An unset {@code POWERED_BIT} returns {@code 0}, without scanning the
     * tripwire line again.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(POWERED_BIT) ? MAX_REDSTONE_POWER : 0;
    }

    /**
     * Returns maximum strong power only toward the support behind a powered hook. Other
     * faces, unpowered snapshots, and direction values without a horizontal face return
     * {@code 0}.
     */
    @Override
    public int getStrongPower(Block block, BlockFace face) {
        if (!block.getPropertyValue(POWERED_BIT)) {
            return 0;
        }

        BlockFace hookFacing = getHookFacing(block);
        if (hookFacing != null && face == hookFacing.opposite()) {
            return MAX_REDSTONE_POWER;
        }
        return 0;
    }

    /**
     * Returns the hook's horizontal facing decoded from its direction property. An
     * out-of-range property value returns {@code null}, allowing callers with invalid
     * snapshots to skip attachment or line processing.
     */
    private BlockFace getHookFacing(Block block) {
        int direction = block.getPropertyValue(DIRECTION_4);
        return BlockFace.fromHorizontalIndex(direction);
    }

    /**
     * Recalculates endpoint attachment, endpoint power, and intervening wire attachment
     * from one hook snapshot. {@code onBreak} forces disconnection and suppresses
     * rewriting this endpoint. The scan continues across missing segments to locate a
     * farther opposite hook, but any ordinary gap invalidates the complete line.
     *
     * <p>
     * {@code pos} selects an optional line offset, with {@code -1} selecting ordinary
     * scanning. A non-{@code null} {@code wireBlock} substitutes the segment snapshot at that
     * offset, although the live scanned type still controls whether its armed and
     * powered bits are sampled. A selected live wire requests a two-tick recheck and
     * can invalidate connection when disarmed.
     *
     * <p>
     * The opposite hook is updated first when its state differs, then this hook unless
     * breaking, then collected wires when attachment changed. A missing opposite hook
     * at the recheck aborts the remaining writes. {@code updateAround} controls
     * additional explicit notifications at this endpoint; dimension writes can still
     * queue their usual notifications, and the opposite endpoint's explicit
     * notifications are unconditional when it changes. Refused writes are not rolled
     * back.
     */
    public void calculateState(Block block, boolean onBreak, boolean updateAround, int pos, Block wireBlock) {
        BlockFace hookFacing = getHookFacing(block);
        if (hookFacing == null) {
            return;
        }

        Dimension dimension = block.getDimension();
        Vector3ic hookPos = block.getPosition();

        boolean wasAttached = block.getPropertyValue(ATTACHED_BIT);
        boolean wasPowered = block.getPropertyValue(POWERED_BIT);
        boolean canConnect = !onBreak;
        boolean nextPowered = false;
        int distance = 0;
        Block[] wireBlocks = new Block[MAX_DISTANCE];

        // The exclusive bound limits the far endpoint to offset forty-one;
        // retained snapshots align with these exact offsets when attachment bits are updated later.
        for (int i = 1; i < MAX_DISTANCE; i++) {
            Vector3ic scanPos = hookFacing.offsetPos(hookPos, i);
            Block scannedBlock = new Block(dimension, scanPos);
            BlockType<?> scannedType = scannedBlock.getBlockType();

            if (scannedType == BlockTypes.TRIPWIRE_HOOK) {
                BlockFace otherFacing = getHookFacingFromState(scannedBlock);
                if (otherFacing == hookFacing.opposite()) {
                    distance = i;
                }
                break;
            }

            if (scannedType != BlockTypes.TRIP_WIRE && i != pos) {
                wireBlocks[i] = null;
                canConnect = false;
            } else {
                if (i == pos && wireBlock != null) {
                    scannedBlock = wireBlock;
                }

                if (scannedType == BlockTypes.TRIP_WIRE) {
                    boolean disarmed = scannedBlock.getPropertyValue(DISARMED_BIT);
                    boolean wirePowered = scannedBlock.getPropertyValue(POWERED_BIT);
                    nextPowered |= (!disarmed && wirePowered);

                    if (i == pos) {
                        // An explicitly selected live wire can represent a transient
                        // change. Recheck after two game ticks instead of treating this observation as permanent.
                        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(hookPos, Duration.ofMillis(100));
                        canConnect &= !disarmed;
                    }
                }
                wireBlocks[i] = scannedBlock;
            }
        }

        // Adjacent hooks have no intervening detection segment. Require at least
        // one wire position before allowing collected powered bits to drive endpoint output.
        canConnect = canConnect && distance > 1;
        nextPowered = nextPowered && canConnect;

        if (distance > 0) {
            Vector3ic otherHookPos = hookFacing.offsetPos(hookPos, distance);
            BlockState otherHookState = dimension.getBlockState(otherHookPos);

            if (otherHookState.getBlockType() != BlockTypes.TRIPWIRE_HOOK) {
                return;
            }

            boolean otherAttached = otherHookState.getPropertyValue(ATTACHED_BIT);
            boolean otherPowered = otherHookState.getPropertyValue(POWERED_BIT);

            // A hook write invokes placement callbacks that can scan this same
            // pair again. Skip unchanged endpoints so an already settled pair does not keep rewriting itself.
            if (otherAttached != canConnect || otherPowered != nextPowered) {
                BlockFace otherFacing = hookFacing.opposite();

                BlockState newOtherState = otherHookState
                        .setPropertyValue(ATTACHED_BIT, canConnect)
                        .setPropertyValue(POWERED_BIT, nextPowered);

                dimension.setBlockState(otherHookPos.x(), otherHookPos.y(), otherHookPos.z(), newOtherState);
                dimension.updateAround(otherHookPos);
                dimension.updateAround(otherFacing.opposite().offsetPos(otherHookPos));

                addSound(dimension, otherHookPos, canConnect, nextPowered, otherAttached, otherPowered);
            }
        }

        if (!onBreak) {
            // The opposite endpoint may have triggered a nested line evaluation.
            // Compare this supplied snapshot before requesting another write and its associated placement callbacks.
            if (wasAttached != canConnect || wasPowered != nextPowered) {
                addSound(dimension, hookPos, canConnect, nextPowered, wasAttached, wasPowered);

                BlockState newState = block.getBlockState()
                        .setPropertyValue(ATTACHED_BIT, canConnect)
                        .setPropertyValue(POWERED_BIT, nextPowered);

                dimension.setBlockState(hookPos.x(), hookPos.y(), hookPos.z(), newState);

                if (updateAround) {
                    dimension.updateAround(hookPos);
                    dimension.updateAround(hookFacing.opposite().offsetPos(hookPos));
                }
            }
        }

        if (wasAttached != canConnect) {
            for (int i = 1; i < distance; i++) {
                Block wire = wireBlocks[i];
                if (wire != null && wire.getBlockType() == BlockTypes.TRIP_WIRE) {
                    Vector3ic wirePos = hookFacing.offsetPos(hookPos, i);
                    BlockState wireState = dimension.getBlockState(wirePos);

                    BlockState newWireState = wireState.setPropertyValue(ATTACHED_BIT, canConnect);
                    dimension.setBlockState(wirePos.x(), wirePos.y(), wirePos.z(), newWireState);
                }
            }
        }
    }

    /**
     * Returns the horizontal facing encoded by a scanned hook snapshot. Out-of-range
     * direction values return {@code null}, which cannot match the opposite-facing
     * endpoint required by connection scanning.
     */
    private BlockFace getHookFacingFromState(Block block) {
        int direction = block.getPropertyValue(DIRECTION_4);
        return BlockFace.fromHorizontalIndex(direction);
    }

    /**
     * Plays the highest-priority click implied by attachment and power transitions.
     * Becoming powered selects the on sound before attachment changes are considered,
     * and losing power selects the off sound before detachment changes.
     *
     * <p>
     * When power is unchanged, attaching selects the on sound and detaching selects the
     * off sound. A stable state emits no sound. This ordered selection produces at most
     * one sound for a hook update even when attachment and power change together.
     */
    private void addSound(Dimension dimension, Vector3ic pos, boolean canConnect, boolean nextPowered, boolean wasAttached, boolean wasPowered) {
        Block block = new Block(dimension, pos);

        if (nextPowered && !wasPowered) {
            block.addSound(SimpleSound.POWER_ON);
        } else if (!nextPowered && wasPowered) {
            block.addSound(SimpleSound.POWER_OFF);
        } else if (canConnect && !wasAttached) {
            block.addSound(SimpleSound.POWER_ON);
        } else if (!canConnect && wasAttached) {
            block.addSound(SimpleSound.POWER_OFF);
        }
    }
}
