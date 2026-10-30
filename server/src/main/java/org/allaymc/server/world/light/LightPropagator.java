package org.allaymc.server.world.light;

import org.allaymc.api.block.data.BlockFace;

/**
 * {@code LightPropagator} updates neighboring illumination after a source
 * grows or shrinks. Increase work spreads only into dimmer neighbors, reducing
 * light by at least one at every step. Decrease work first clears weaker
 * dependent values, while neighbors bright enough to represent other
 * illumination seed a subsequent increase pass. The separate queues preserve
 * those surviving contributions instead of erasing every neighbor of a removed
 * source.
 *
 * <p>
 * Each propagator reuses its queues and one mutable entry, so its caller
 * serializes propagation operations. Chunk data may disappear during
 * traversal; {@code setLightAndPropagate} suppresses {@code
 * NullPointerException} and abandons that invocation without clearing queued
 * work. A later invocation can therefore encounter remaining entries. This
 * recovery boundary does not roll back light values already written or
 * establish that all removed data will be recalculated immediately.
 *
 * @author daoge_cmd
 */
public class LightPropagator {
    /**
     * {@code BLOCK_FACE_UPDATE_ORDER} supplies the adjacent faces traversed by
     * both propagation passes when checking illumination around each queued
     * position.
     */
    protected static final BlockFace[] BLOCK_FACE_UPDATE_ORDER = BlockFace.VALUES;

    /**
     * Accessor for the propagated channel and block properties.
     */
    protected final LightDataAccessor lightDataAccessor;
    // lightDecreaseQueue clears weaker dependent values before lightIncreaseQueue
    // spreads surviving brightness. Both queues and entry belong to the same
    // serialized propagation operation and remain allocated between calls.
    /**
     * Positions awaiting outward illumination growth.
     */
    protected final LightUpdateQueue lightIncreaseQueue;
    /**
     * Positions awaiting dependent illumination removal.
     */
    protected final LightUpdateQueue lightDecreaseQueue;
    /**
     * {@code entry} holds the current dequeued tuple and is reused by both
     * passes, requiring serialized propagation calls.
     */
    protected final LightUpdateQueue.LightUpdateEntry entry;

    /**
     * Creates a propagator with reusable work queues for the supplied data
     * channel. The accessor is retained without copying, and callers serialize
     * operations because the queues and current entry are mutable.
     */
    protected LightPropagator(LightDataAccessor lightDataAccessor) {
        this.lightDataAccessor = lightDataAccessor;
        this.lightIncreaseQueue = new LightUpdateQueue();
        this.lightDecreaseQueue = new LightUpdateQueue();
        this.entry = new LightUpdateQueue.LightUpdateEntry();
    }

    /**
     * Applies a source value and propagates the difference through adjacent
     * blocks. Callers provide available source data, serialize calls, and keep
     * {@code oldLightValue} and {@code newLightValue} from {@code 0} through
     * {@code 15}. A greater value selects increase work, while an equal or
     * smaller value selects the decrease path.
     *
     * <p>
     * A decrease retains brighter neighbors as seeds for repropagation after
     * dependent values are cleared. Any {@code NullPointerException} during
     * either path is suppressed, leaving partial writes and unprocessed queue
     * entries in place; the operation does not provide rollback or a
     * completion guarantee after missing data.
     */
    public void setLightAndPropagate(int x, int y, int z, int oldLightValue, int newLightValue) {
        try {
            if (oldLightValue != newLightValue) {
                lightDataAccessor.setLight(x, y, z, newLightValue);
            }
            if (newLightValue > oldLightValue) {
                lightIncreaseQueue.add(x, y, z, newLightValue);
                propagateIncrease();
            } else {
                lightDecreaseQueue.add(x, y, z, oldLightValue);
                propagateDecrease();
            }
        } catch (NullPointerException ignored) {
            // Chunk removal can discard arrays while propagation traverses them.
            // Abandon this pass without clearing its remaining entries; subsequent
            // operations can revisit those queued positions after partial writes.
        }
    }

    /**
     * Spreads queued brightness into neighbors that the current position can
     * brighten. Every propagation step loses at least one level, or the
     * neighbor's greater dampening, preventing an unchanged value from
     * spreading indefinitely. Heights outside the dimension are skipped before
     * channel access.
     *
     * <p>
     * The method compares existing illumination before writing so brighter
     * paths remain intact. Newly brightened positions join the same queue,
     * allowing the pass to continue outward; callers serialize this traversal
     * with other uses of the shared queues and {@code entry}.
     */
    protected void propagateIncrease() {
        while (!lightIncreaseQueue.isEmpty()) {
            lightIncreaseQueue.poll(this.entry);
            for (var blockFace : BLOCK_FACE_UPDATE_ORDER) {
                var offset = blockFace.getOffset();
                var ox = this.entry.x + offset.x();
                var oy = this.entry.y + offset.y();
                var oz = this.entry.z + offset.z();
                if (!lightDataAccessor.isYInRange(oy)) {
                    continue;
                }
                int neighborLightValue = lightDataAccessor.getLight(ox, oy, oz);
                if (neighborLightValue >= (this.entry.lightValue - 1)) {
                    // Every step loses at least one light level, so this neighbor
                    // already matches or exceeds any contribution from the current entry.
                    continue;
                }
                int newNeighborLightValue = this.entry.lightValue - Math.max(1, lightDataAccessor.getLightDampening(ox, oy, oz));
                if (newNeighborLightValue > neighborLightValue) {
                    lightDataAccessor.setLight(ox, oy, oz, newNeighborLightValue);
                    lightIncreaseQueue.add(ox, oy, oz, newNeighborLightValue);
                }
            }
        }
    }

    /**
     * Clears illumination that depends on queued positions whose brightness
     * has decreased. A nonzero neighbor weaker than the removed value is
     * cleared and queued for further removal, while an equally bright or
     * brighter neighbor seeds the increase queue. Heights outside the
     * dimension are skipped before access.
     *
     * <p>
     * The increase pass runs only after the decrease queue drains, allowing
     * surviving illumination to refill cleared positions. This ordering
     * preserves contributions arriving from other paths instead of allowing a
     * removal wave to erase the final light result.
     */
    protected void propagateDecrease() {
        while (!lightDecreaseQueue.isEmpty()) {
            lightDecreaseQueue.poll(this.entry);
            for (var blockFace : BLOCK_FACE_UPDATE_ORDER) {
                var offset = blockFace.getOffset();
                var ox = this.entry.x + offset.x();
                var oy = this.entry.y + offset.y();
                var oz = this.entry.z + offset.z();
                if (!lightDataAccessor.isYInRange(oy)) {
                    continue;
                }
                int currentNeighborLightValue = lightDataAccessor.getLight(ox, oy, oz);
                if (currentNeighborLightValue != 0 && currentNeighborLightValue < this.entry.lightValue) {
                    lightDataAccessor.setLight(ox, oy, oz, 0);
                    lightDecreaseQueue.add(ox, oy, oz, currentNeighborLightValue);
                } else if (currentNeighborLightValue >= this.entry.lightValue) {
                    lightIncreaseQueue.add(ox, oy, oz, currentNeighborLightValue);
                }
            }
        }
        // Refill cleared positions only after the removal wave finishes, using
        // surviving bright neighbors as seeds rather than reintroducing removed paths.
        propagateIncrease();
    }
}
