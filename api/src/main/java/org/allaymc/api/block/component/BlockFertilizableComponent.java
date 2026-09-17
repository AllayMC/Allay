package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Component for blocks that can be fertilized with bone meal or similar fertilizers.
 */
public interface BlockFertilizableComponent extends BlockComponent {
    /**
     * Applies bone meal to the block at the given position.
     *
     * @param dimension  the dimension the block is in
     * @param pos        the position of the block
     * @param blockState the current state of the block
     * @return true if growth was applied, false if the block could not grow
     */
    boolean onBoneMealUsed(Dimension dimension, Vector3ic pos, BlockState blockState);
}