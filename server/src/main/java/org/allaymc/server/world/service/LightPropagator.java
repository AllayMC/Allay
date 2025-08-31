package org.allaymc.server.world.service;

import org.allaymc.api.block.data.BlockFace;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author daoge_cmd
 */
public class LightPropagator {
    protected static final BlockFace[] BLOCK_FACE_UPDATE_ORDER = BlockFace.values();

    protected final Queue<LightUpdateEntry> lightIncreaseQueue = new LinkedList<>();
    protected final Queue<LightUpdateEntry> lightDecreaseQueue = new LinkedList<>();
    protected final LightDataAccessor lightDataAccessor;

    protected LightPropagator(LightDataAccessor lightDataAccessor) {
        this.lightDataAccessor = lightDataAccessor;
    }

    public void setLightAndPropagate(int x, int y, int z, int oldLightValue, int newLightValue) {
        try {
            if (oldLightValue != newLightValue) {
                lightDataAccessor.setLight(x, y, z, newLightValue);
            }
            if (newLightValue > oldLightValue) {
                lightIncreaseQueue.add(new LightUpdateEntry(x, y, z, newLightValue));
                propagateIncrease();
            } else {
                lightDecreaseQueue.add(new LightUpdateEntry(x, y, z, oldLightValue));
                propagateDecrease();
            }
        } catch (NullPointerException ignored) {
            // It is possible to have NullPointerException occur during light propagation.
            // This is because the chunks we touched is unloaded and the related light data
            // is removed in "Light Calculating Thread (Chunk & Block)" which is not the same
            // thread as the one we are currently running. Since the chunks we touched is
            // unloaded, we can safely ignore this exception and leave directly (light
            // in these chunks will be recalculated when they can be calculated again)
        }
    }

    protected void propagateIncrease() {
        while (!lightIncreaseQueue.isEmpty()) {
            var entry = lightIncreaseQueue.poll();
            var x = entry.x();
            var y = entry.y();
            var z = entry.z();
            var lightValue = entry.lightValue();
            for (var blockFace : BLOCK_FACE_UPDATE_ORDER) {
                var offset = blockFace.getOffset();
                var ox = x + offset.x();
                var oy = y + offset.y();
                var oz = z + offset.z();
                if (!lightDataAccessor.isYInRange(oy)) {
                    continue;
                }
                int neighborLightValue = lightDataAccessor.getLight(ox, oy, oz);
                if (neighborLightValue >= (lightValue - 1)) {
                    // quick short circuit for when the light value is already greater-than where we could set it
                    continue;
                }
                int newNeighborLightValue = lightValue - Math.max(1, lightDataAccessor.getLightDampening(ox, oy, oz));
                if (newNeighborLightValue > neighborLightValue) {
                    // sometimes the neighbour is brighter, maybe it's a source we're propagating.
                    lightDataAccessor.setLight(ox, oy, oz, newNeighborLightValue);
                    lightIncreaseQueue.add(new LightUpdateEntry(ox, oy, oz, newNeighborLightValue));
                }
            }
        }
    }

    protected void propagateDecrease() {
        while (!lightDecreaseQueue.isEmpty()) {
            var entry = lightDecreaseQueue.poll();
            var x = entry.x();
            var y = entry.y();
            var z = entry.z();
            var lightValue = entry.lightValue();
            for (var blockFace : BLOCK_FACE_UPDATE_ORDER) {
                var offset = blockFace.getOffset();
                var ox = x + offset.x();
                var oy = y + offset.y();
                var oz = z + offset.z();
                if (!lightDataAccessor.isYInRange(oy)) {
                    continue;
                }
                int currentNeighborLightValue = lightDataAccessor.getLight(ox, oy, oz);
                if (currentNeighborLightValue != 0 && currentNeighborLightValue < lightValue) {
                    lightDataAccessor.setLight(ox, oy, oz, 0);
                    lightDecreaseQueue.add(new LightUpdateEntry(ox, oy, oz, currentNeighborLightValue));
                } else if (currentNeighborLightValue >= lightValue) {
                    lightIncreaseQueue.add(new LightUpdateEntry(ox, oy, oz, currentNeighborLightValue));
                }
            }
        }
        propagateIncrease();
    }

    protected record LightUpdateEntry(int x, int y, int z, int lightValue) {
    }
}
