package org.allaymc.api.world.manager;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.joml.Vector3ic;

import java.time.Duration;

/**
 * {@code BlockUpdateManager} schedules
 * timed block callbacks and queues neighbor changes for one
 * dimension. Timed requests use absolute world ticks or delays converted to
 * ticks, while random-speed-aware delays scale with the world game rule and
 * neighbor requests retain the previous neighbor state when known.
 *
 * @author Cool_Loong
 */
public interface BlockUpdateManager {

    /**
     * Schedules a block update after converting the duration to whole
     * fifty-millisecond ticks. Fractional ticks truncate toward zero, and
     * duration conversion can throw an arithmetic exception when nanoseconds
     * overflow.
     *
     * @see #scheduleBlockUpdate(Vector3ic, long)
     */
    default void scheduleBlockUpdateInDelay(Vector3ic pos, Duration delay) {
        // Fifty-millisecond ticks discard sub-tick duration instead of rounding
        // the deadline upward.
        scheduleBlockUpdateInDelay(pos, delay.toNanos() / 50_000_000);
    }

    /**
     * Schedules an update at dimension block coordinates after the supplied
     * delay in world ticks. The deadline is the current world tick plus this
     * delay, rather than an independent wall-clock time.
     */
    void scheduleBlockUpdateInDelay(Vector3ic pos, long delay);

    /**
     * Schedules an update at dimension block coordinates for an absolute world
     * tick. The supplied value is a deadline rather than a delay;
     * implementations define handling when the containing chunk is unavailable.
     */
    void scheduleBlockUpdate(Vector3ic pos, long time);

    /**
     * Converts the duration to whole fifty-millisecond ticks, narrows that
     * count to an integer, and delegates to the random-speed-aware overload.
     * Fractional ticks are discarded, and very large tick counts can wrap
     * during narrowing.
     *
     * @see #scheduleRandomBlockUpdateInDelay(Vector3ic, long)
     */
    default void scheduleRandomBlockUpdateInDelay(Vector3ic pos, Duration delay) {
        // This overload narrows whole ticks to int before delegation, so long
        // durations can wrap even though the receiving overload accepts a long
        // delay.
        scheduleRandomBlockUpdateInDelay(pos, (int) (delay.toNanos() / 50_000_000));
    }

    /**
     * Schedules an update using a delay scaled by the world's random tick
     * speed. The supplied delay is measured in ticks before scaling; behaviors
     * such as fire use this to change their update rate with the game rule.
     */
    void scheduleRandomBlockUpdateInDelay(Vector3ic pos, long delay);

    /**
     * Returns whether an update is pending at the supplied dimension block
     * coordinates. The query does not execute or reserve the update, and an
     * unavailable chunk can be reported as having no scheduled work.
     */
    boolean hasScheduledBlockUpdate(Vector3ic pos);

    /**
     * Queues a neighbor-change notification for the target dimension block
     * position. The changed neighbor position and face identify the cause, and
     * {@code oldNeighborState} may be {@code null} when its previous state is unknown.
     */
    void neighborBlockUpdate(Vector3ic pos, Vector3ic changedNeighbour, BlockFace blockFace, BlockState oldNeighborState);
}
