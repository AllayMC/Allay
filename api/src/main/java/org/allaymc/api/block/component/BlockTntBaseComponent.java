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
     * Primes the TNT with a custom fuse duration.
     *
     * @param blockStateWithPos the {@link BlockStateWithPos} to prime
     * @param fuse              the fuse duration in ticks
     */
    void prime(BlockStateWithPos blockStateWithPos, int fuse);
}
