package org.allaymc.server.world.manager;

import io.netty.util.internal.PlatformDependent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.block.BlockNeighborUpdateEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.manager.BlockUpdateManager;
import org.joml.Vector3ic;

import java.util.Queue;

/**
 * {@code AllayBlockUpdateManager} schedules
 * chunk-local timed updates and drains queued neighbor notifications
 * for one dimension. Timed requests target currently loaded chunks, while
 * neighbor requests retain supplied position references until the dimension
 * tick processes them.
 *
 * <p>
 * Neighbor draining captures the initial queue size and applies a per-tick cap,
 * limiting callbacks that can recursively enqueue further work. Each entry
 * dispatches the primary layer first and then a liquid secondary layer when
 * present. Cancelling either event stops the entire drain for that tick after
 * consuming the current entry. Random-speed-aware scheduling divides the
 * requested delay in floating point, so zero or negative game-rule values
 * retain Java conversion behavior rather than receiving a separate
 * disabled-update policy.
 *
 * @author daoge_cmd
 */
@Slf4j
@RequiredArgsConstructor
public class AllayBlockUpdateManager implements BlockUpdateManager {
    /**
     * {@code MAX_NEIGHBOR_UPDATE_PER_TICK} caps
     * neighbor entries processed in one tick so recursive notifications
     * cannot monopolize the entire drain.
     */
    public static final int MAX_NEIGHBOR_UPDATE_PER_TICK = 65535;

    /**
     * Dimension receiving scheduled and neighbor updates.
     */
    protected final Dimension dimension;
    /**
     * {@code neighborUpdates} queues incoming requests and retains their
     * position references. A single tick consumer drains bounded batches from
     * this concurrent queue.
     */
    protected final Queue<NeighborUpdate> neighborUpdates = PlatformDependent.newMpscQueue();

    /**
     * Processes the bounded neighbor-update batch for the current dimension
     * tick.
     */
    public void tick() {
        tickNeighborUpdates();
    }

    /**
     * Converts the supplied tick delay to an absolute deadline using the
     * current world tick. The addition is not overflow-checked, and scheduling
     * then follows the loaded-chunk requirement of the absolute overload.
     */
    @Override
    public void scheduleBlockUpdateInDelay(Vector3ic pos, long delay) {
        scheduleBlockUpdate(pos, dimension.getWorld().getTick() + delay);
    }

    /**
     * Schedules an absolute world-tick update in the currently loaded chunk
     * containing the dimension position. An unavailable chunk logs a warning
     * and drops the request rather than starting a load.
     */
    @Override
    public void scheduleBlockUpdate(Vector3ic pos, long time) {
        var chunk = dimension.getChunkManager().getChunkByDimensionPos(pos.x(), pos.z());
        if (chunk == null) {
            // Scheduling does not load missing chunks, so this request is
            // dropped and warned about instead of being retained for an
            // uncertain future chunk lifetime.
            log.warn("Failed to schedule block update at {} because chunk is not loaded", pos);
            return;
        }

        // Masking horizontal coordinates preserves the local position within
        // the already selected chunk.
        chunk.addScheduledUpdate(pos.x() & 15, pos.y(), pos.z() & 15, time);
    }

    /**
     * Schedules an ordinary delayed update after dividing the requested delay
     * by the random-tick-speed rule using floats. The quotient is cast to {@code long}
     * without special handling for zero, negative, infinite, or imprecise
     * results; absolute deadline addition follows afterward.
     */
    @Override
    public void scheduleRandomBlockUpdateInDelay(Vector3ic pos, long delay) {
        // Float division scales this callback delay with the game rule; Java's
        // float-to-long conversion also determines the zero-speed and overflow
        // cases without a separate fallback.
        scheduleBlockUpdateInDelay(pos, (long) ((float) delay / (float) dimension.getWorld().getWorldData().<Integer>getGameRuleValue(GameRule.RANDOM_TICK_SPEED)));
    }

    /**
     * Returns whether a currently loaded chunk has a pending update for the
     * dimension position. An unavailable chunk returns {@code false} without loading
     * it; otherwise horizontal coordinates are converted to local coordinates.
     */
    @Override
    public boolean hasScheduledBlockUpdate(Vector3ic pos) {
        var chunk = dimension.getChunkManager().getChunkByDimensionPos(pos.x(), pos.z());
        if (chunk == null) {
            // An unavailable chunk supplies no accessible schedule map, so this
            // lookup returns false without loading data or distinguishing
            // absent work from unavailable work.
            return false;
        }

        return chunk.hasScheduledUpdate(pos.x() & 15, pos.y(), pos.z() & 15);
    }

    /**
     * Enqueues a neighbor-change descriptor retaining both supplied position
     * references and the optional previous state. Callers must keep those
     * positions stable until processing because the record does not copy
     * vectors.
     */
    @Override
    public void neighborBlockUpdate(Vector3ic pos, Vector3ic changedNeighbour, BlockFace blockFace, BlockState oldNeighborState) {
        neighborUpdates.add(new NeighborUpdate(pos, changedNeighbour, blockFace, oldNeighborState));
    }

    /**
     * Processes at most the captured initial queue size and the configured
     * per-tick cap. A consumed entry resolves current primary and secondary
     * states, dispatches the primary event and behavior, then handles the
     * secondary layer only when its behavior is liquid. Event cancellation
     * returns from the whole method rather than skipping just that entry.
     * Remaining entries stay queued, while the consumed entry and any completed
     * primary behavior are not restored.
     */
    protected void tickNeighborUpdates() {
        int count = 0;
        // Capturing the initial size bounds recursive enqueueing: newly appended
        // notifications normally remain behind this batch instead of extending it
        // indefinitely during the same tick.
        int initialNeighborUpdateCount = neighborUpdates.size();
        NeighborUpdate update;
        while (count < initialNeighborUpdateCount && count < MAX_NEIGHBOR_UPDATE_PER_TICK && (update = neighborUpdates.poll()) != null) {
            var blockFace = update.blockFace();
            var pos = update.pos();
            var neighborPos = update.neighborPos();
            var oldNeighborState = update.oldNeighborState();

            var layer0 = dimension.getBlockState(pos);
            var layer1 = dimension.getBlockState(pos, 1);

            var block0 = new Block(layer0, new Position3i(pos, dimension), 0);
            var neighborBlock0 = new Block(dimension.getBlockState(neighborPos), new Position3i(neighborPos, dimension), 0);

            // Cancelling the primary event ends this entire drain after
            // consuming its entry; queued neighbors remain pending, and the
            // secondary layer is skipped as well.
            if (!callNeighborUpdateEvent(block0, neighborBlock0, blockFace, oldNeighborState)) {
                return;
            }

            layer0.getBehavior().onNeighborUpdate(block0, neighborBlock0, blockFace, oldNeighborState);

            // Secondary-layer dispatch is restricted to liquid behavior, matching
            // waterlogged block handling while excluding unrelated extra-layer states.
            if (layer1.getBehavior() instanceof BlockLiquidBehavior) {
                var block1 = new Block(layer1, new Position3i(pos, dimension), 1);

                if (!callNeighborUpdateEvent(block1, neighborBlock0, blockFace, oldNeighborState)) {
                    return;
                }

                layer1.getBehavior().onNeighborUpdate(block1, neighborBlock0, blockFace, oldNeighborState);
            }

            count++;
        }
    }

    /**
     * Dispatches a neighbor-update event and returns whether processing may
     * continue. A cancelled event returns {@code false}, which the current drain treats
     * as a reason to stop the entire batch.
     */
    protected boolean callNeighborUpdateEvent(Block block, Block neighbor, BlockFace blockFace, BlockState oldNeighborState) {
        return new BlockNeighborUpdateEvent(block, neighbor, blockFace, oldNeighborState).call();
    }

    /**
     * {@code NeighborUpdate} carries
     * a deferred neighbor-change request without copying its supplied
     * positions. The queue consumer resolves current block states when this
     * descriptor is processed.
     *
     * @param pos {@code pos} holds the retained target position. Callers keep
     *            its coordinates stable until the queued update is processed.
     * @param neighborPos {@code neighborPos} holds the changed-neighbor position.
     *                    Callers keep its coordinates stable until processing.
     * @param blockFace face affected by the change
     * @param oldNeighborState {@code oldNeighborState} holds the previous state.
     *                         The producer supplies {@code null} when that state
     *                         is unknown.
     */
    protected record NeighborUpdate(Vector3ic pos, Vector3ic neighborPos, BlockFace blockFace, BlockState oldNeighborState) {
    }
}
