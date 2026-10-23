package org.allaymc.api.world.light;

import org.joml.Vector3ic;

/**
 * {@code LightEngine} exposes propagated sky and block illumination together
 * with the effective light used by gameplay. Raw skylight describes access to
 * sky sources and remains independent of time and weather. Block light
 * describes local emission, so daylight attenuation must affect only the sky
 * contribution before the two sources are compared. Queue size reports pending
 * recalculation work rather than proving that all light values are current.
 *
 * <p>
 * An engine that calculates light reduces raw skylight using the current time
 * and weather, clamps that contribution at zero, and selects the greater of
 * effective skylight and block light. Dimensions without skylight use block
 * light alone. An implementation used when light calculation is disabled may
 * instead report full illumination for every light query.
 *
 * @author daoge_cmd
 */
public interface LightEngine {

    /**
     * Returns raw propagated skylight at dimension block coordinates. Time and
     * weather do not attenuate this value, so a position exposed to the sky
     * can retain level {@code 15} at night.
     */
    int getSkyLight(int x, int y, int z);

    /**
     * Returns raw skylight at the supplied dimension block coordinates.
     */
    default int getSkyLight(Vector3ic pos) {
        return getSkyLight(pos.x(), pos.y(), pos.z());
    }

    /**
     * Returns the effective illumination used by light-dependent gameplay. A
     * calculating engine compares block light with sky light after time and
     * weather attenuation, rather than attenuating the maximum of both raw
     * sources. This distinction keeps local emitters bright at night.
     *
     * <p>
     * Dimensions without skylight use only the block contribution. The result
     * reflects currently available propagation data; callers that need
     * completed recalculation must separately coordinate with the
     * implementation's update processing.
     */
    int getInternalLight(int x, int y, int z);

    /**
     * Returns effective gameplay illumination at the supplied dimension block
     * coordinates.
     */
    default int getInternalLight(Vector3ic pos) {
        return getInternalLight(pos.x(), pos.y(), pos.z());
    }

    /**
     * Returns the effective skylight contribution used by gameplay after
     * environmental attenuation. A calculating engine subtracts the reduction
     * derived from time and weather from raw skylight and prevents a negative
     * result. This keeps the stored propagation values independent of changes
     * to the day cycle.
     *
     * <p>
     * Dimensions without skylight have no sky contribution. Implementations
     * that disable calculation can provide a constant full-light policy
     * instead, as described by their own contracts.
     */
    int getInternalSkyLight(int x, int y, int z);

    /**
     * Returns attenuated gameplay skylight at the supplied dimension block
     * coordinates.
     */
    default int getInternalSkyLight(Vector3ic pos) {
        return getInternalSkyLight(pos.x(), pos.y(), pos.z());
    }

    /**
     * Returns propagated block-emitted illumination at the supplied dimension
     * block coordinates.
     */
    int getBlockLight(int x, int y, int z);

    /**
     * Returns block-emitted illumination at the supplied dimension block
     * coordinates.
     */
    default int getBlockLight(Vector3ic pos) {
        return getBlockLight(pos.x(), pos.y(), pos.z());
    }

    /**
     * Returns the current count of queued light recalculation tasks. Running
     * tasks are excluded, and concurrent producers and consumers can change
     * the count immediately, so zero does not establish completed propagation.
     */
    int getQueuedUpdateCount();
}
