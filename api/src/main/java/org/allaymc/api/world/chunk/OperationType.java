package org.allaymc.api.world.chunk;

/**
 * {@code OperationType} selects
 * the access mode that a chunk operation requires for blocks or biomes.
 * Chunk wrappers interpret each selection independently, allowing a callback to
 * request shared reads, exclusive writes, or no lock for data it does not
 * access.
 *
 * @author daoge_cmd
 */
public enum OperationType {
    /**
     * No lock requested for this data category.
     */
    NONE,
    /**
     * Shared read access to this data category.
     */
    READ,
    /**
     * Exclusive write access to this data category.
     */
    WRITE
}
