package org.allaymc.api.block.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author IWareQ
 */
public interface BlockConcretePowderBaseComponent extends BlockBaseComponent {
    /**
     * Retrieves the concrete block type that this powder block transforms into when exposed to water.
     *
     * @return the block type of the solid concrete variant
     */
    BlockType<?> getSolidBlock();

    /**
     * Checks if there is water adjacent to the given block position, excluding the block below.
     *
     * @param dimension the dimension in which the block exists
     * @param blockPos  the position of the block being checked
     *
     * @return {@code true} if there is water adjacent to the block, {@code false} otherwise
     */
    default boolean hasAdjacentWater(Dimension dimension, Vector3ic blockPos) {
        for (var face : BlockFace.VALUES) {
            if (face == BlockFace.DOWN) {
                continue;
            }

            var liquid = dimension.getLiquid(face.offsetPos(blockPos));
            if (liquid.right() != null) {
                return true;
            }
        }

        return false;
    }
}
