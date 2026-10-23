package org.allaymc.server.world.light;

import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;

/**
 * {@code NoLightEngine} supplies constant full illumination for dimensions
 * with light calculation disabled. The engine does not start calculation
 * workers or enqueue chunk and block updates, so its backlog remains empty
 * while every sky, block, and gameplay light query reports level {@code 15}.
 *
 * @author daoge_cmd
 */
public class NoLightEngine extends AllayLightEngine {

    /**
     * Creates the full-light policy engine for the supplied dimension. The
     * superclass allocates ordinary engine state, but this implementation's
     * lifecycle and update overrides do not start or feed calculation workers.
     */
    public NoLightEngine(Dimension dimension) {
        super(dimension, false);
    }

    /**
     * Returns level {@code 15} as raw skylight at every supplied coordinate.
     * This disabled-calculation policy ignores position, dimension skylight
     * support, time, and weather when answering the query.
     */
    @Override
    public int getSkyLight(int x, int y, int z) {
        return 15;
    }

    /**
     * Returns level {@code 15} as block illumination at every supplied
     * coordinate. This disabled-calculation policy ignores position, dimension
     * skylight support, time, and weather when answering the query.
     */
    @Override
    public int getBlockLight(int x, int y, int z) {
        return 15;
    }

    /**
     * Returns level {@code 15} as effective gameplay illumination at every
     * supplied coordinate. This disabled-calculation policy ignores position,
     * dimension skylight support, time, and weather when answering the query.
     */
    @Override
    public int getInternalLight(int x, int y, int z) {
        return 15;
    }

    /**
     * Returns level {@code 15} as effective gameplay skylight at every
     * supplied coordinate. This disabled-calculation policy ignores position,
     * dimension skylight support, time, and weather when answering the query.
     */
    @Override
    public int getInternalSkyLight(int x, int y, int z) {
        return 15;
    }

    /**
     * Leaves calculation workers unstarted for this full-light policy engine.
     */
    @Override
    public void startTick() {
    }

    /**
     * Ignores block changes because this engine never recalculates
     * illumination.
     */
    @Override
    public void onBlockChange(int x, int y, int z, int le, int ld) {
    }

    /**
     * Ignores chunk admission because this engine stores no calculated chunk
     * light.
     */
    @Override
    public void onChunkLoad(Chunk chunk) {
    }

    /**
     * Ignores chunk removal because this engine stores no calculated chunk
     * light.
     */
    @Override
    public void onChunkUnload(Chunk chunk) {
    }

    /**
     * Returns zero because this engine never queues light calculation work.
     */
    @Override
    public int getQueuedUpdateCount() {
        return 0;
    }
}
