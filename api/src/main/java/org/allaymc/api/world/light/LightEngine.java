package org.allaymc.api.world.light;

import org.joml.Vector3ic;

/**
 * LightEngine is responsible for storing and calculating the light of a dimension.
 *
 * @author daoge_cmd
 * @see <a href="https://minecraft.wiki/w/Light">Light</a>
 */
public interface LightEngine {

    /**
     * Get the raw skylight level at the specified position.
     * <p>
     * This value only represents sky light propagation in the dimension. It is not reduced by
     * the current time of day or weather, so a position that is directly exposed to the sky
     * can still return {@code 15} at night.
     *
     * @param x the x coordinate of the pos
     * @param y the y coordinate of the pos
     * @param z the z coordinate of the pos
     * @return the raw skylight level at the specified position
     */
    int getSkyLight(int x, int y, int z);

    /**
     * @see #getSkyLight(int, int, int)
     */
    default int getSkyLight(Vector3ic pos) {
        return getSkyLight(pos.x(), pos.y(), pos.z());
    }

    /**
     * Get the effective light level at the specified position for internal game logic.
     * <p>
     * This is the combined light level used by most light-dependent behavior. In dimensions with
     * skylight, it is the greater of {@linkplain #getInternalSkyLight(int, int, int) internal sky
     * light} and {@linkplain #getBlockLight(int, int, int) block light}. In dimensions without
     * skylight, this is equal to block light.
     *
     * @param x the x coordinate of the pos
     * @param y the y coordinate of the pos
     * @param z the z coordinate of the pos
     * @return the effective internal light level at the specified position
     */
    int getInternalLight(int x, int y, int z);

    /**
     * @see #getInternalLight(int, int, int)
     */
    default int getInternalLight(Vector3ic pos) {
        return getInternalLight(pos.x(), pos.y(), pos.z());
    }

    /**
     * Get the effective skylight level at the specified position for internal game logic.
     * <p>
     * Unlike {@link #getSkyLight(int, int, int)}, this value includes the reduction caused by the
     * current time of day and weather. It represents how much sky light is actually available to
     * gameplay systems after day-night and weather attenuation is applied.
     *
     * @param x the x coordinate of the pos
     * @param y the y coordinate of the pos
     * @param z the z coordinate of the pos
     * @return the effective internal skylight level at the specified position
     */
    int getInternalSkyLight(int x, int y, int z);

    /**
     * @see #getInternalSkyLight(int, int, int)
     */
    default int getInternalSkyLight(Vector3ic pos) {
        return getInternalSkyLight(pos.x(), pos.y(), pos.z());
    }

    /**
     * Get the block light level at the specified position.
     * <p>
     * Block light is emitted by blocks or other local light sources and is independent of sky
     * access, time of day, and weather.
     *
     * @param x the x coordinate of the pos
     * @param y the y coordinate of the pos
     * @param z the z coordinate of the pos
     * @return the block light level at the specified position
     */
    int getBlockLight(int x, int y, int z);

    /**
     * @see #getBlockLight(int, int, int)
     */
    default int getBlockLight(Vector3ic pos) {
        return getBlockLight(pos.x(), pos.y(), pos.z());
    }

    /**
     * Get the number of light update tasks that are currently waiting to be processed.
     * <p>
     * This includes queued recalculation work such as chunk light initialization or block-triggered
     * light propagation updates. It can be used to estimate the current backlog of the light engine.
     *
     * @return the number of queued light update tasks
     */
    int getQueuedUpdateCount();
}
