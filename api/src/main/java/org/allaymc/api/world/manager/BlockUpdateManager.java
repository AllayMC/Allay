package org.allaymc.api.world.manager;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.joml.Vector3ic;

import java.time.Duration;

/**
 * BlockUpdateManager is responsible for handling block updates.
 *
 * @author Cool_Loong
 */
public interface BlockUpdateManager {

    /**
     * @see #scheduleBlockUpdate(Vector3ic, long)
     */
    default void scheduleBlockUpdateInDelay(Vector3ic pos, Duration delay) {
        scheduleBlockUpdateInDelay(pos, delay.toNanos() / 50_000_000);
    }

    /**
     * Schedule a block update at the specified position. Different from {@link #scheduleBlockUpdate(Vector3ic, long)},
     * this method will set the time of the scheduled update to the current world time plus the delay.
     *
     * @param pos   The position of the block to update
     * @param delay The delay of the block update, in ticks
     */
    void scheduleBlockUpdateInDelay(Vector3ic pos, long delay);

    /**
     * Schedule a block update at the specified position.
     *
     * @param pos  The position of the block to update
     * @param time The time that the block update will be executed, in ticks
     */
    void scheduleBlockUpdate(Vector3ic pos, long time);

    /**
     * @see #scheduleRandomBlockUpdateInDelay(Vector3ic, long)
     */
    default void scheduleRandomBlockUpdateInDelay(Vector3ic pos, Duration delay) {
        scheduleRandomBlockUpdateInDelay(pos, (int) (delay.toNanos() / 50_000_000));
    }

    /**
     * Schedule a random block update at the specified position. Calling this method is considered
     * equivalent to {@code scheduleBlockUpdate(pos, (int) ((float) delay / (float) randomTickSpeed), layer)}.
     * <p>
     * Unlike {@link #scheduleBlockUpdateInDelay(Vector3ic, long)}, in this method the delay will also
     * be divided by the random tick speed of the current world. For example, this method is used
     * by fire, so changing the random tick speed will also change the speed of fire spreading.
     *
     * @param pos   The position of the block to update
     * @param delay The delay of the block update, in ticks
     */
    void scheduleRandomBlockUpdateInDelay(Vector3ic pos, long delay);

    /**
     * Check if a block update is scheduled at the specified position.
     *
     * @param pos The position of the block to check
     * @return Whether a block update is scheduled at the specified position
     */
    boolean hasScheduledBlockUpdate(Vector3ic pos);

    /**
     * Add a neighbor block update at the specified position.
     *
     * @param pos              The position of the block which will be updated
     * @param changedNeighbour The position of the block which causes the update
     * @param blockFace        The face which will be updated
     * @param oldNeighborState The previous block state at changedNeighbour position (null if unknown)
     */
    void neighborBlockUpdate(Vector3ic pos, Vector3ic changedNeighbour, BlockFace blockFace, BlockState oldNeighborState);
}
