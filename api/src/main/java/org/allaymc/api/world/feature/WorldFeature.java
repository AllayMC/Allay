package org.allaymc.api.world.feature;

import org.allaymc.api.utils.identifier.Identifier;

/**
 * WorldFeature represents a world feature that can be placed in the world.
 * Features can be used during world generation (Populator stage) or at runtime
 * (e.g., sapling growth, bone meal usage).
 *
 * @author daoge_cmd
 */
public interface WorldFeature {

    /**
     * Get the identifier of this feature.
     *
     * @return the feature identifier
     */
    Identifier getIdentifier();

    /**
     * Place this feature at the specified position.
     *
     * @param context the feature placement context
     * @param x       the x coordinate (world position)
     * @param y       the y coordinate (world position)
     * @param z       the z coordinate (world position)
     *
     * @return true if the feature was successfully placed, false otherwise
     */
    boolean place(WorldFeatureContext context, int x, int y, int z);
}
