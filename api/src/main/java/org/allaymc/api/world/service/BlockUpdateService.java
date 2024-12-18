package org.allaymc.api.world.service;

import org.allaymc.api.block.data.BlockFace;
import org.joml.Vector3ic;

import java.time.Duration;

/**
 * BlockUpdateService is responsible for handling block updates.
 *
 * @author Cool_Loong
 */
public interface BlockUpdateService {

    /**
     * Schedule a block update at the specified position.
     *
     * @param pos   The position of the block to update.
     * @param delay The delay of the block update, in ticks.
     */
    default void scheduleBlockUpdate(Vector3ic pos, int delay) {
        scheduleBlockUpdate(pos, delay, 0);
    }

    /**
     * Schedule a block update at the specified position.
     *
     * @param pos   The position of the block to update.
     * @param delay The delay of the block update, in ticks.
     * @param layer The layer of the block update.
     */
    void scheduleBlockUpdate(Vector3ic pos, int delay, int layer);

    /**
     * Schedule a block update at the specified position.
     *
     * @param pos   The position of the block to update
     * @param delay The delay of the block update
     */
    default void scheduleBlockUpdate(Vector3ic pos, Duration delay) {
        scheduleBlockUpdate(pos, delay, 0);
    }

    /**
     * Schedule a block update at the specified position.
     *
     * @param pos   The position of the block to update
     * @param delay The delay of the block update
     * @param layer The layer of the block update.
     */
    default void scheduleBlockUpdate(Vector3ic pos, Duration delay, int layer) {
        scheduleBlockUpdate(pos, (int) (delay.toNanos() / 50_000_000), layer);
    }

    /**
     * Check if a block update is scheduled at the specified position.
     *
     * @param pos The position of the block to check.
     *
     * @return Whether a block update is scheduled at the specified position.
     */
    default boolean hasScheduledBlockUpdate(Vector3ic pos) {
        return hasScheduledBlockUpdate(pos, 0);
    }

    /**
     * Check if a block update is scheduled at the specified position.
     *
     * @param pos   The position of the block to check.
     * @param layer The layer of the block update.
     *
     * @return Whether a block update is scheduled at the specified position.
     */
    boolean hasScheduledBlockUpdate(Vector3ic pos, int layer);

    /**
     * Add a neighbor block update at the specified position.
     *
     * @param pos              The position of the block which will be updated.
     * @param changedNeighbour The position of the block which causes the update.
     * @param blockFace        The face which will be updated.
     */
    void neighborBlockUpdate(Vector3ic pos, Vector3ic changedNeighbour, BlockFace blockFace);
}
