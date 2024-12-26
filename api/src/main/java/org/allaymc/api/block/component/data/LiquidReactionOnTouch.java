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
     */
    BLOCKING,
    /**
     * The block won't have any reaction, also won't have any impact on the liquid just like air.
     */
    NOREACTION
}
