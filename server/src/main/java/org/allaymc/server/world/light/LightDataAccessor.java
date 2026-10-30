package org.allaymc.server.world.light;

/**
 * {@code LightDataAccessor} supplies propagation access to illumination,
 * emission, and dampening using dimension block coordinates. Callers check
 * vertical bounds and arrange chunk availability before reading or writing
 * data. Dampening mutation stays with the engine because it can also require
 * updating skylight source heights.
 *
 * @author daoge_cmd
 */
public interface LightDataAccessor {
    /**
     * Returns propagated illumination at the supplied dimension block
     * coordinates. Callers arrange available chunk data and an in-range
     * height; this operation does not load missing chunks for propagation.
     */
    int getLight(int x, int y, int z);

    /**
     * Stores propagated illumination at the supplied dimension block
     * coordinates. Callers arrange available chunk data, serialize channel
     * mutations, supply an in-range height, and keep {@code value} between
     * {@code 0} and {@code 15}.
     */
    void setLight(int x, int y, int z, int value);

    /**
     * Returns block attenuation at the supplied dimension block coordinates.
     * Callers arrange available chunk data and an in-range height; dampening
     * values describe the destination block's effect on propagation.
     */
    int getLightDampening(int x, int y, int z);

    /**
     * Returns block emission at the supplied dimension block coordinates.
     * Callers arrange available chunk data and an in-range height; the value
     * describes a local source before neighboring propagation.
     */
    int getLightEmission(int x, int y, int z);

    /**
     * Stores block emission without scheduling propagation work for the
     * supplied coordinates. Callers arrange available chunk data, serialize
     * channel mutations, supply an in-range height, and keep {@code value}
     * between {@code 0} and {@code 15}.
     */
    void setLightEmission(int x, int y, int z, int value);

    /**
     * Returns whether the supplied height belongs to this accessor's
     * dimension.
     */
    boolean isYInRange(int y);
}
