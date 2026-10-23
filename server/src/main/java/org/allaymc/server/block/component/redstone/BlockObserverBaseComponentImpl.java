package org.allaymc.server.block.component.redstone;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftFacingDirection;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_FACING_DIRECTION;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.POWERED_BIT;

/**
 * {@code BlockObserverBaseComponentImpl} detects neighbor notifications on its observed
 * face and emits delayed directional redstone pulses from the opposite face. Placement
 * selects the observed face from vertical pitch first and normalized yaw sectors
 * otherwise.
 * {@code POWERED_BIT} distinguishes activation from deactivation when a scheduled
 * callback arrives. {@code ACTIVATION_DELAY} requests activation after four game ticks,
 * while {@code PULSE_DURATION} requests release after the following two game ticks;
 * both intervals feed the same position-based scheduler.
 *
 * <p>
 * An observer ignores observed-face notifications while powered or while any update is
 * already scheduled at its position. Activation requests the powered state before
 * notifying neighbors and then schedules the end of the pulse. Neighbor notifications
 * are queued by the dimension rather than dispatched recursively here. Piston movement
 * restores an observer at another position without transferring its original timer, so
 * {@code onMoved} releases a powered snapshot immediately or schedules activation for
 * an unpowered one.
 *
 * @author daoge_cmd
 */
public class BlockObserverBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Interval between accepted detection and requested pulse activation.
     */
    protected static final Duration ACTIVATION_DELAY = Duration.ofMillis(200);
    /**
     * Powered interval before the requested pulse release.
     */
    protected static final Duration PULSE_DURATION = Duration.ofMillis(100);

    /**
     * Creates an observer component for the supplied registered block type.
     */
    public BlockObserverBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places an observer with its observed face selected from player pitch and yaw. A
     * {@code null} {@code placementInfo} preserves the supplied facing and skips this
     * orientation calculation.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            MinecraftFacingDirection facing = getPlacementFacing(placementInfo);
            blockState = blockState.setPropertyValue(MINECRAFT_FACING_DIRECTION, facing);
        }
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Schedules activation after an accepted notification from the observed face. Other
     * faces and powered snapshots are ignored after inherited handling. The position
     * must also have no scheduled block update, regardless of which operation
     * originally requested it.
     *
     * <p>
     * That pending-update guard coalesces further notifications during the activation
     * delay instead of replacing the deadline. Once the callback activates the
     * observer, {@code POWERED_BIT} suppresses notifications during the pulse;
     * activation schedules a separate callback to release that bit.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        BlockFace observedFace = getObservedFace(block);
        if (face != observedFace) {
            return;
        }

        if (block.getPropertyValue(POWERED_BIT)) {
            return;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();

        // Keep the existing deadline when another observed-face change arrives
        // during the delay; replacing it would postpone activation until changes stop.
        if (dimension.getBlockUpdateManager().hasScheduledBlockUpdate(pos)) {
            return;
        }

        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, ACTIVATION_DELAY);
    }

    /**
     * Requests the powered state, notifies the observer's neighbors and output-side
     * neighborhood, and schedules deactivation. The property write precedes queued
     * notifications, allowing later neighbor callbacks to observe an active pulse when
     * the write succeeds.
     */
    protected void activate(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        // Publish the active bit before queuing neighbor callbacks so those
        // callbacks can suppress another activation while this pulse is in progress.
        dimension.updateBlockProperty(POWERED_BIT, true, pos);

        BlockFace outputFace = getOutputFace(block);
        dimension.updateAround(pos);
        dimension.updateAround(outputFace.offsetPos(pos));

        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, PULSE_DURATION);
    }

    /**
     * Requests an unpowered observer state and notifies affected neighboring circuits.
     * The output-side neighborhood is notified in addition to the observer's immediate
     * neighbors, and this helper does not request another pulse.
     */
    protected void deactivate(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        dimension.updateBlockProperty(POWERED_BIT, false, pos);

        BlockFace outputFace = getOutputFace(block);
        dimension.updateAround(pos);
        dimension.updateAround(outputFace.offsetPos(pos));
    }

    /**
     * Uses the supplied powered bit to choose the next pulse phase. A powered snapshot
     * is released, while an unpowered snapshot is activated and schedules its later
     * release. The callback does not retain an explicit activation-versus-release
     * token.
     *
     * <p>
     * The block update manager dispatches using the state present at the scheduled
     * position. Consequently this decision depends on the callback's current snapshot,
     * rather than the state or cause that originally created the scheduled entry.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        if (block.getPropertyValue(POWERED_BIT)) {
            deactivate(block);
        } else {
            activate(block);
        }
    }

    /**
     * Restores observer pulse handling after a piston places the observer at its
     * destination. A powered snapshot is deactivated immediately because the original
     * position's scheduled release is not transferred with the block.
     *
     * <p>
     * An unpowered snapshot requests activation only when its destination has no
     * scheduled update. This destination guard coalesces movement with already pending
     * work at the new position; it does not cancel or migrate an entry that remains at
     * the old position.
     */
    @Override
    public void onMoved(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        if (block.getPropertyValue(POWERED_BIT)) {
            deactivate(block);
            return;
        }

        if (!dimension.getBlockUpdateManager().hasScheduledBlockUpdate(pos)) {
            dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, ACTIVATION_DELAY);
        }
    }

    /**
     * Returns maximum weak power only from the output face of a powered observer.
     * Unpowered snapshots and other queried faces return {@code 0}, leaving the
     * observed face without emitted power.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        if (!block.getPropertyValue(POWERED_BIT)) {
            return 0;
        }

        BlockFace outputFace = getOutputFace(block);
        if (face == outputFace) {
            return MAX_REDSTONE_POWER;
        }
        return 0;
    }

    /**
     * Returns the same directional power as {@code getWeakPower}. The observer's output
     * face supplies strong power while its snapshot is powered, and every other face
     * returns {@code 0}.
     */
    @Override
    public int getStrongPower(Block block, BlockFace face) {
        return getWeakPower(block, face);
    }

    /**
     * Returns the observed face encoded in this block's facing property.
     */
    protected BlockFace getObservedFace(Block block) {
        MinecraftFacingDirection facing = block.getPropertyValue(MINECRAFT_FACING_DIRECTION);
        return toBlockFace(facing);
    }

    /**
     * Returns the face opposite the observer's observed front face.
     */
    protected BlockFace getOutputFace(Block block) {
        return getObservedFace(block).opposite();
    }

    /**
     * Selects the observed face by giving vertical pitch precedence over horizontal
     * yaw. Pitch greater than {@code 45} selects {@code DOWN}, and pitch below
     * {@code -45} selects {@code UP}. Exact boundary pitches continue to horizontal
     * selection because both comparisons are strict.
     *
     * <p>
     * For finite horizontal angles, yaw normalization maps equivalent full turns into
     * the same four sectors, including negative yaw. The sectors center on south, west,
     * north, and east in increasing yaw order. Vertical cases return before this
     * mapping, so yaw affects only placements that did not already select a vertical
     * observed face.
     */
    protected MinecraftFacingDirection getPlacementFacing(PlayerInteractInfo placementInfo) {
        var player = placementInfo.player();
        var pitch = player.getLocation().pitch();

        // The vertical thresholds reserve shallow viewing angles for horizontal placement.
        if (pitch > 45) {
            return MinecraftFacingDirection.DOWN;
        } else if (pitch < -45) {
            return MinecraftFacingDirection.UP;
        }

        var yaw = player.getLocation().yaw();
        // Normalize negative yaw into the same sectors as positive yaw.
        yaw = ((yaw % 360) + 360) % 360;

        // Half-quadrant boundaries center each horizontal face on its cardinal yaw.
        if (yaw >= 315 || yaw < 45) {
            return MinecraftFacingDirection.SOUTH;
        } else if (yaw >= 45 && yaw < 135) {
            return MinecraftFacingDirection.WEST;
        } else if (yaw >= 135 && yaw < 225) {
            return MinecraftFacingDirection.NORTH;
        } else {
            return MinecraftFacingDirection.EAST;
        }
    }

    /**
     * Returns the block-face equivalent of the observer's facing direction encoding.
     */
    protected BlockFace toBlockFace(MinecraftFacingDirection direction) {
        return switch (direction) {
            case DOWN -> BlockFace.DOWN;
            case UP -> BlockFace.UP;
            case NORTH -> BlockFace.NORTH;
            case SOUTH -> BlockFace.SOUTH;
            case WEST -> BlockFace.WEST;
            case EAST -> BlockFace.EAST;
        };
    }
}
