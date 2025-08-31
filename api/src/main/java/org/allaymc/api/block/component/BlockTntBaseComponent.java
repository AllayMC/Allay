package org.allaymc.api.block.component;

import org.allaymc.api.block.dto.Block;

/**
 * @author daoge_cmd
 */
public interface BlockTntBaseComponent extends BlockBaseComponent {

    /**
     * @see #prime(Block, int)
     */
    default void prime(Block block) {
        this.prime(block, 80);
    }

    /**
     * Primes the TNT with a custom fuse duration.
     *
     * @param block the {@link Block} to prime
     * @param fuse  the fuse duration in ticks
     */
    void prime(Block block, int fuse);
}
