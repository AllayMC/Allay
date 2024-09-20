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
     * @param pos The position of the block to update
     * @param delay The delay of the block update, in ticks
     */
    void scheduleBlockUpdate(Vector3ic pos, long delay);

    /**
     * Schedule a block update at the specified position.
     *
     * @param pos The position of the block to update
     * @param delay The delay of the block update
     */
    default void scheduleBlockUpdate(Vector3ic pos, Duration delay) {
        scheduleBlockUpdate(pos, delay.toNanos() / 50_000_000);
    }

    /**
     * Add a neighbor block update at the specified position.
     *
     * @param pos The position of the block which will be updated.
     * @param changedNeighbour The position of the block which causes the update.
     * @param blockFace The face which will be updated.
     */
    void neighborBlockUpdate(Vector3ic pos, Vector3ic changedNeighbour, BlockFace blockFace);
}
