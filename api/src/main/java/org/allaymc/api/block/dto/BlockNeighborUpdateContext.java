package org.allaymc.api.block.dto;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;

/**
 * Context object containing all information about a block neighbor update.
 * <p>
 * This context is passed to {@link org.allaymc.api.block.component.BlockBaseComponent#onNeighborUpdate}
 * when a neighboring block changes and triggers an update.
 * </p>
 * <p>
 * Using a context object instead of individual parameters allows the API to be
 * extended in the future without breaking existing implementations. New information
 * can be added to this context as needed without modifying method signatures.
 * </p>
 *
 * @param block            the block being updated
 * @param neighbor         the neighboring block that triggered the update
 * @param face             the face of the block being updated (direction from block to neighbor)
 * @param oldNeighborState the previous block state at the neighbor position, may be null if unknown
 *
 * @author ClexaGod
 */
public record BlockNeighborUpdateContext(
        Block block,
        Block neighbor,
        BlockFace face,
        BlockState oldNeighborState
) {
}
