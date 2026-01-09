package org.allaymc.server.world.light;

import org.allaymc.api.block.data.BlockFace;

/**
 * @author daoge_cmd
 */
public class LightPropagator {
    protected static final BlockFace[] BLOCK_FACE_UPDATE_ORDER = BlockFace.VALUES;

    protected final LightDataAccessor lightDataAccessor;
    protected final LightUpdateQueue lightIncreaseQueue;
    protected final LightUpdateQueue lightDecreaseQueue;
    protected final LightUpdateQueue.LightUpdateEntry entry;

    protected LightPropagator(LightDataAccessor lightDataAccessor) {
        this.lightDataAccessor = lightDataAccessor;
        this.lightIncreaseQueue = new LightUpdateQueue();
        this.lightDecreaseQueue = new LightUpdateQueue();
        this.entry = new LightUpdateQueue.LightUpdateEntry();
    }

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
            // It is possible to have NullPointerException occur during light propagation.
            // This is because the chunks we touched are unloaded and the related light data
            // is removed in "Light Calculating Thread (Chunk & Block)" which is a different
            // thread as the one we are currently running. Since the chunks we touched are
            // unloaded, we can safely ignore this exception and leave directly (light
            // in these chunks will be recalculated when they can be calculated again)
        }
    }

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
                    // quick short circuit for when the light value is already greater-than where we could set it
                    continue;
                }
                int newNeighborLightValue = this.entry.lightValue - Math.max(1, lightDataAccessor.getLightDampening(ox, oy, oz));
                if (newNeighborLightValue > neighborLightValue) {
                    // sometimes the neighbour is brighter, maybe it's a source we're propagating.
                    lightDataAccessor.setLight(ox, oy, oz, newNeighborLightValue);
                    lightIncreaseQueue.add(ox, oy, oz, newNeighborLightValue);
                }
            }
        }
    }

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
        propagateIncrease();
    }
}
