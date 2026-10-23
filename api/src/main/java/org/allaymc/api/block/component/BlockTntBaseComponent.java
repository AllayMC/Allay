package org.allaymc.api.block.component;

import org.allaymc.api.math.position.Position3ic;

/**
 * {@code BlockTntBaseComponent} exposes operations that request a TNT block's
 * transition into an explosive entity. Callers supply the world position and
 * optionally a fuse in game ticks; the convenience overload selects an eighty-tick
 * fuse. The server implementation attempts block removal and requests deferred entity
 * addition without confirming either outcome.
 *
 * @author daoge_cmd
 */
public interface BlockTntBaseComponent extends BlockBaseComponent {

    /**
     * Requests TNT priming at the supplied world position with an eighty-tick fuse.
     * The custom-fuse overload performs the request; the server implementation does
     * not confirm block removal or deferred entity admission before returning.
     */
    default void prime(Position3ic pos) {
        this.prime(pos, 80);
    }

    /**
     * Requests TNT priming at the supplied world position using a fuse measured in
     * ticks. The server implementation attempts to replace the block with air before
     * requesting deferred entity addition, without confirming removal or admission.
     */
    void prime(Position3ic pos, int fuse);
}
