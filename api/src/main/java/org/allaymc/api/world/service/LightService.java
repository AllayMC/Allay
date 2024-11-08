package org.allaymc.api.world.service;

/**
 * LightService is responsible for storing and calculating the light of a dimension.
 *
 * @author daoge_cmd
 * @see <a href="https://minecraft.wiki/w/Light">Light</a>
 */
public interface LightService {
    /**
     * Get the skylight level at the specified position.
     *
     * @param x the x coordinate of the pos.
     * @param y the y coordinate of the pos.
     * @param z the z coordinate of the pos.
     * @return the skylight level at the specified position.
     */
    int getSkyLight(int x, int y, int z);

    /**
     * Get the internal light level at the specified position.
     *
     * @param x the x coordinate of the pos.
     * @param y the y coordinate of the pos.
     * @param z the z coordinate of the pos.
     * @return the internal light level at the specified position.
     */
    int getInternalLight(int x, int y, int z);

    /**
     * Get the internal skylight level at the specified position.
     *
     * @param x the x coordinate of the pos.
     * @param y the y coordinate of the pos.
     * @param z the z coordinate of the pos.
     * @return the internal skylight level at the specified position.
     */
    int getInternalSkyLight(int x, int y, int z);

    /**
     * Get the block light level at the specified position.
     *
     * @param x the x coordinate of the pos.
     * @param y the y coordinate of the pos.
     * @param z the z coordinate of the pos.
     * @return the block light level at the specified position.
     */
    int getBlockLight(int x, int y, int z);

    /**
     * Get the queued update count.
     *
     * @return the queued update count.
     */
    int getQueuedUpdateCount();
}
