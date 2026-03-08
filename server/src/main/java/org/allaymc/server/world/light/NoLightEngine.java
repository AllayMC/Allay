package org.allaymc.server.world.light;

import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;

/**
 * A light engine that always returns max light value (15). Used when light calculation is disabled for a dimension.
 *
 * @author daoge_cmd
 */
public class NoLightEngine extends AllayLightEngine {

    public NoLightEngine(Dimension dimension) {
        super(dimension, false);
    }

    @Override
    public int getSkyLight(int x, int y, int z) {
        return 15;
    }

    @Override
    public int getBlockLight(int x, int y, int z) {
        return 15;
    }

    @Override
    public int getInternalLight(int x, int y, int z) {
        return 15;
    }

    @Override
    public int getInternalSkyLight(int x, int y, int z) {
        return 15;
    }

    @Override
    public void startTick() {
        // No-op: skip starting calculation threads
    }

    @Override
    public void onBlockChange(int x, int y, int z, int le, int ld) {
        // No-op: skip light recalculation
    }

    @Override
    public void onChunkLoad(Chunk chunk) {
        // No-op: skip chunk light calculation
    }

    @Override
    public void onChunkUnload(Chunk chunk) {
        // No-op: skip cleanup
    }

    @Override
    public int getQueuedUpdateCount() {
        return 0;
    }
}
