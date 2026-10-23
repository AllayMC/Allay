package org.allaymc.api.block.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * {@code BlockConcretePowderBaseComponent} exposes the solid counterpart and
 * adjacent-liquid check used by concrete powder hardening. The adjacency helper
 * excludes the lower face and accepts any liquid returned by the dimension, including
 * lava, rather than distinguishing water.
 *
 * @author IWareQ
 */
public interface BlockConcretePowderBaseComponent extends BlockBaseComponent {
    /**
     * Returns the solid concrete block type corresponding to this powder.
     */
    BlockType<?> getSolidBlock();

    /**
     * Returns whether a liquid occupies a neighboring position above or beside the
     * block. The check excludes the position below and accepts any liquid reported by
     * {@code dimension.getLiquid}, including lava.
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
