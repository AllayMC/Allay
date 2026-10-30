package org.allaymc.server.block.component.redstone;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.REDSTONE_SIGNAL;

/**
 * {@code BlockDaylightDetectorBaseComponentImpl} calculates and stores a daylight
 * detector's weak redstone output from sky light and world time. Periodic callbacks
 * sample the light above the detector and apply a piecewise day-night factor before
 * optionally inverting the result. The fixed {@code inverted} flag belongs to the
 * registered detector variant. {@code TICKS_PER_DAY} and {@code NOON} use game ticks
 * from the same dawn origin: {@code calculateSignal} folds time into that cycle, and
 * {@code getSunFactor} locates its daytime peak.
 *
 * <p>
 * Player interaction requests the opposite detector type while carrying its current
 * signal into the replacement. The immediate recalculation still runs through this
 * component and therefore uses its existing {@code inverted} flag; later callbacks on
 * the replacement use that type's component. Signal writes and notifications are
 * requested only when the calculated result differs from the live stored signal, and
 * scheduled callbacks continue requesting another sample.
 *
 * @author daoge_cmd
 */
public class BlockDaylightDetectorBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code UPDATE_INTERVAL} controls recurring daylight sampling, allowing time and
     * sky-light changes to update detector output without a neighbor notification.
     */
    protected static final Duration UPDATE_INTERVAL = Duration.ofMillis(1000);
    /**
     * Game ticks in one daylight calculation cycle.
     */
    protected static final int TICKS_PER_DAY = 24000;
    /**
     * Day-cycle tick of the daytime brightness peak.
     */
    protected static final int NOON = 6000;

    /**
     * {@code inverted} selects whether calculated internal light is subtracted from
     * maximum power. This fixed variant identity also applies to immediate
     * recalculation after an interaction.
     */
    protected final boolean inverted;

    /**
     * Creates a daylight detector component using the supplied fixed inversion mode.
     */
    public BlockDaylightDetectorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean inverted) {
        super(blockType);
        this.inverted = inverted;
    }

    /**
     * Starts periodic sampling after inherited placement handling finishes. This
     * callback requests the first delayed update at the placed position rather than
     * calculating and storing a signal immediately.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        scheduleUpdate(oldBlock.getDimension(), oldBlock.getPosition());
    }

    /**
     * Requests the opposite detector variant while preserving the current signal in its
     * initial state. Inherited handling can consume the interaction first, and a
     * {@code null} {@code interactInfo} returns {@code false}.
     *
     * <p>
     * After requesting replacement, this same component recalculates output using its
     * own {@code inverted} flag, even though the requested type has the opposite mode.
     * The method does not check replacement success and returns {@code true};
     * subsequent scheduled dispatch uses the component of the block then occupying the
     * position.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }
        if (interactInfo == null) {
            return false;
        }

        var block = interactInfo.getClickedBlock();
        var pos = block.getPosition();
        int currentSignal = block.getPropertyValue(REDSTONE_SIGNAL);

        BlockType<?> newType = inverted ? BlockTypes.DAYLIGHT_DETECTOR : BlockTypes.DAYLIGHT_DETECTOR_INVERTED;
        BlockState newState = newType.getDefaultState().setPropertyValue(REDSTONE_SIGNAL, currentSignal);
        dimension.setBlockState(pos.x(), pos.y(), pos.z(), newState);

        // This callback still belongs to the old variant after replacement;
        // its immediate calculation uses the old inversion flag until later dispatch.
        updateSignal(dimension, pos);

        return true;
    }

    /**
     * Recalculates the stored detector signal and requests the next periodic sample.
     * The new schedule is requested after calculation, so an exception during
     * calculation prevents this callback from scheduling another update.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        updateSignal(block.getDimension(), block.getPosition());
        scheduleUpdate(block.getDimension(), block.getPosition());
    }

    /**
     * Requests a new stored signal only when it differs from the live detector state.
     * Calculation uses this component's inversion mode, and a changed result notifies
     * neighboring circuits without checking property-update success.
     */
    protected void updateSignal(Dimension dimension, Vector3ic pos) {
        int newSignal = calculateSignal(dimension, pos);
        BlockState currentState = dimension.getBlockState(pos);
        int currentSignal = currentState.getPropertyValue(REDSTONE_SIGNAL);

        if (newSignal != currentSignal) {
            dimension.updateBlockProperty(REDSTONE_SIGNAL, newSignal, pos);
            dimension.updateAround(pos);
        }
    }

    /**
     * Calculates output from sky light above the detector, folded world time, and this
     * variant's inversion mode. The light engine supplies brightness at
     * {@code pos.y() + 1}, and the world time is reduced modulo {@code TICKS_PER_DAY}
     * before sunlight scaling.
     *
     * <p>
     * {@code calculateInternalLight} rounds and clamps the scaled brightness to the
     * redstone range. A normal detector returns that value; an inverted detector
     * subtracts it from {@code 15}, so darkness and midnight can produce maximum
     * inverted output.
     */
    protected int calculateSignal(Dimension dimension, Vector3ic pos) {
        int skyLight = dimension.getLightEngine().getSkyLight(pos.x(), pos.y() + 1, pos.z());

        // The sunlight curve repeats once per game day, independently of the
        // world's accumulated time across previous days.
        int timeOfDay = dimension.getWorld().getWorldData().getTimeOfDay() % TICKS_PER_DAY;

        int internalLight = calculateInternalLight(skyLight, timeOfDay);

        if (inverted) {
            return 15 - internalLight;
        }
        return internalLight;
    }

    /**
     * Scales sky light by the piecewise sunlight factor and rounds to the nearest
     * integer. Zero sky light returns {@code 0} before the time-dependent factor is
     * calculated. The rounded result is then clamped to the redstone range {@code 0}
     * through {@code 15}.
     *
     * <p>
     * Brightness and world time therefore both affect the stored output. For example,
     * sky light {@code 15} at time {@code 0} is halved and rounds to {@code 8}, while
     * the same sky light at {@code NOON} remains {@code 15}.
     */
    protected int calculateInternalLight(int skyLight, int timeOfDay) {
        if (skyLight == 0) {
            return 0;
        }

        double sunFactor = getSunFactor(timeOfDay);

        // Nearest-integer rounding keeps fractional sunlight representable as a stored signal.
        int effectiveLight = (int) Math.round(skyLight * sunFactor);

        // The signal property accepts only the redstone range.
        return Math.clamp(effectiveLight, 0, 15);
    }

    /**
     * Returns the piecewise sunlight factor for a time measured in game ticks. The
     * daytime half peaks at {@code NOON}, while the nighttime half decreases to
     * midnight and then rises toward dawn. Normal day-cycle inputs therefore range from
     * zero at midnight to one at noon.
     */
    protected double getSunFactor(int timeOfDay) {

        // These linear segments approximate a day-night brightness cycle:
        // daylight rises from one half to noon's peak, while night reaches zero at midnight.
        if (timeOfDay >= 0 && timeOfDay < 12000) {
            double distFromNoon = Math.abs(timeOfDay - NOON);
            return 1.0 - (distFromNoon / 6000.0) * 0.5;
        } else {
            double nightProgress = (timeOfDay - 12000) / 12000.0;
            if (nightProgress < 0.5) {
                return 0.5 - nightProgress;
            } else {
                return nightProgress - 0.5;
            }
        }
    }

    /**
     * Requests another detector sample after {@code UPDATE_INTERVAL} at the supplied
     * position. The dimension's block update manager owns the schedule and dispatches
     * the callback using the block present there later.
     */
    protected void scheduleUpdate(Dimension dimension, Vector3ic pos) {
        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, UPDATE_INTERVAL);
    }

    /**
     * Returns the detector's stored signal as weak power on every queried face. This
     * read uses the supplied block snapshot and does not recalculate sky light or world
     * time.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(REDSTONE_SIGNAL);
    }
}
