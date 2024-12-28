package org.allaymc.api.block.component.data;

/**
 * @author daoge_cmd
 */
public enum LiquidReactionOnTouch {
    /**
     * The block will be broken without dropping anything.
     */
    BROKEN,
    /**
     * The block will drop itself.
     */
    POPPED,
    /**
     * The block will prevent the liquid from flowing into.
     * Compared to {@link BlockStateData#canContainLiquidSource}, this only
     * affects flowing liquid, not source liquid. That's mean the block
     * may still can contain source liquid block.
     */
    BLOCKING,
    /**
     * The block won't have any reaction, also won't have any impact on the liquid just like air.
     */
    NOREACTION;

    /**
     * Check if the block should be removed when touched by liquid.
     *
     * @return true if the block should be removed when touched by liquid.
     */
    public boolean removedOnTouch() {
        return this == BROKEN || this == POPPED;
    }
}
