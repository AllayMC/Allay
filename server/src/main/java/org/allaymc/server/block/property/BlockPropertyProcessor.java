package org.allaymc.server.block.property;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.joml.Vector3ic;

/**
 * {@code BlockPropertyProcessor} derives block-state property values from placement
 * coordinates and player interaction information. Shared processors allow placement
 * behavior to apply the same orientation or placement rule to several block types
 * while returning the selected state for the caller to place.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface BlockPropertyProcessor {
    /**
     * Returns the state selected from the supplied placement context. The caller
     * remains responsible for placing that state in the dimension, and each
     * processor defines which properties and interaction information it requires.
     */
    BlockState process(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo);
}
