package org.allaymc.api.world.dimension;

import org.allaymc.api.utils.identifier.Identifier;

/**
 * Represents a dimension type.
 *
 * @author daoge_cmd
 */
public interface DimensionType {
    /**
     * Get the numeric id of the dimension type.
     *
     * @return the numeric id
     */
    int getId();

    /**
     * Get the identifier of the dimension type.
     *
     * @return the identifier
     */
    Identifier getIdentifier();

    /**
     * Get the minimum build height of the dimension type.
     *
     * @return the minimum build height
     */
    int getMinHeight();

    /**
     * Get the maximum build height of the dimension type.
     *
     * @return the maximum build height
     */
    int getMaxHeight();

    /**
     * Check whether the dimension type has sky light.
     *
     * @return {@code true} if the dimension type has sky light
     */
    boolean hasSkyLight();

    /**
     * Check whether beds work normally in the dimension type.
     *
     * @return {@code true} if beds work normally
     */
    boolean bedWorks();

    /**
     * Check whether respawn anchors work in the dimension type.
     *
     * @return {@code true} if respawn anchors work
     */
    boolean respawnAnchorWorks();

    /**
     * Check whether water evaporates in the dimension type.
     *
     * @return {@code true} if water evaporates
     */
    boolean waterEvaporates();

    /**
     * Get the number of chunk sections in the dimension type.
     *
     * @return the number of chunk sections
     */
    default int chunkSectionCount() {
        return (getMaxHeight() - getMinHeight() + 1) / 16;
    }

    /**
     * Get the minimum section y of the dimension type.
     *
     * @return the minimum section y
     */
    default int minSectionY() {
        return getMinHeight() >> 4;
    }

    /**
     * Get the maximum section y of the dimension type.
     *
     * @return the maximum section y
     */
    default int maxSectionY() {
        return getMaxHeight() >> 4;
    }
}
