package org.allaymc.api.world.dimension;

import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code DimensionType} defines a dimension's identity, inclusive build-height bounds, and
 * environmental behavior switches. Chunk indexing derives section coordinates
 * from those bounds, so implementations must align the minimum to a
 * sixteen-block section start and the maximum to its final block.
 *
 * @author daoge_cmd
 */
public interface DimensionType {
    /**
     * Returns the numeric identifier assigned to this registered dimension
     * type.
     */
    int getId();

    /**
     * Returns the namespaced identifier used to register this dimension type.
     */
    Identifier getIdentifier();

    /**
     * Returns the inclusive minimum build height in absolute block coordinates.
     */
    int getMinHeight();

    /**
     * Returns the inclusive maximum build height in absolute block coordinates.
     */
    int getMaxHeight();

    /**
     * Returns whether this dimension type supports propagation of sky light.
     */
    boolean hasSkyLight();

    /**
     * Returns whether beds provide normal sleeping behavior in this dimension.
     */
    boolean bedWorks();

    /**
     * Returns whether respawn anchors function as respawn points in this
     * dimension.
     */
    boolean respawnAnchorWorks();

    /**
     * Returns whether placed water evaporates under this dimension's
     * environmental rules.
     */
    boolean waterEvaporates();

    /**
     * Returns the number of sixteen-block sections across the inclusive height
     * range. Bounds must describe complete aligned sections; integer division
     * truncates a partial section instead of validating the range.
     */
    default int chunkSectionCount() {
        // Inclusive block bounds contribute one extra block before conversion
        // to complete sections.
        return (getMaxHeight() - getMinHeight() + 1) / 16;
    }

    /**
     * Returns the absolute section Y containing the minimum build height.
     * Arithmetic shifting preserves floor-based section coordinates for
     * negative heights; implementations must align the minimum to the section
     * start.
     */
    default int minSectionY() {
        // Arithmetic shifting preserves containing-section coordinates below
        // absolute height zero.
        return getMinHeight() >> 4;
    }

    /**
     * Returns the absolute section Y containing the maximum build height.
     * Arithmetic shifting preserves floor-based section coordinates for
     * negative heights; implementations must align the maximum to a section's
     * last block.
     */
    default int maxSectionY() {
        // Arithmetic shifting preserves containing-section coordinates below
        // absolute height zero.
        return getMaxHeight() >> 4;
    }
}
