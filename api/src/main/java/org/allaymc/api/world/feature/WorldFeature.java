package org.allaymc.api.world.feature;

import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code WorldFeature} represents an identified structure or vegetation
 * feature that can be placed through a chunk source. Features serve both
 * generation callbacks and runtime growth, so callers provide the context and
 * coordinate any surrounding world-state changes.
 *
 * @author daoge_cmd
 */
public interface WorldFeature {

    /**
     * Returns the namespaced identifier used to register this feature.
     */
    Identifier getIdentifier();

    /**
     * Attempts to place this feature at dimension block coordinates. The context
     * controls which chunks are accessible, and an accepted attempt can still
     * encounter discarded writes or exceptions without automatic rollback.
     *
     * @param context the feature placement context
     * @param x       the x coordinate (world position)
     * @param y       the y coordinate (world position)
     * @param z       the z coordinate (world position)
     *
     * @return {@code true} when the implementation accepts placement, or
     *         {@code false} when its placement checks reject the request
     */
    boolean place(WorldFeatureContext context, int x, int y, int z);
}
