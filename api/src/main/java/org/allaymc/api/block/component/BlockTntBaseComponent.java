package org.allaymc.api.block.component;

import org.allaymc.api.math.position.Position3ic;

/**
 * Shared component for TNT-like blocks that can be primed into an explosive entity.
 *
 * @author daoge_cmd
 */
public interface BlockTntBaseComponent extends BlockBaseComponent {

    /**
     * @see #prime(Position3ic, int)
     */
    default void prime(Position3ic pos) {
        this.prime(pos, 80);
    }

    /**
     * Primes a TNT at the given pos with a custom fuse duration.
     *
     * @param pos  the pos to summon TNT entity
     * @param fuse the fuse duration in ticks
     */
    void prime(Position3ic pos, int fuse);
}
