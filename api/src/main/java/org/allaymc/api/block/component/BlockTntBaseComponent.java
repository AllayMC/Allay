package org.allaymc.api.block.component;

import org.allaymc.api.block.dto.BlockStateWithPos;

/**
 * @author daoge_cmd
 */
public interface BlockTntBaseComponent extends BlockBaseComponent {

    /**
     * @see #prime(BlockStateWithPos, int)
     */
    default void prime(BlockStateWithPos blockStateWithPos) {
        this.prime(blockStateWithPos, 80);
    }

    /**
     * Prime the TNT at specified pos.
     *
     * @param blockStateWithPos the tnt to prime.
     * @param fuse              the fuse of the tnt.
     */
    void prime(BlockStateWithPos blockStateWithPos, int fuse);
}
