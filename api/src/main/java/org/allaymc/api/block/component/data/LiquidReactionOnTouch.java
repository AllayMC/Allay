package org.allaymc.api.block.component.data;

/**
 * @author daoge_cmd
 */
public enum LiquidReactionOnTouch {
    /**
     * Block breaks without dropping items.
     */
    BROKEN,
    /**
     * Block drops itself when broken.
     */
    POPPED,
    /**
     * The block will prevent the liquid from flowing into.
     * <p>
     * Compared to {@link BlockStateData#canContainLiquidSource}, this only
     * affects flowing liquid, not source liquid. That's mean the block
     * may still can contain source liquid block.
     */
    BLOCKING,
    /**
     * Block has no reaction and does not affect liquid flow, similar to air.
     */
    NOREACTION;

    /**
     * Checks if the block should be removed when touched by liquid.
     *
     * @return {@code true} if removed (BROKEN or POPPED), {@code false} otherwise
     */
    public boolean shouldRemoveOnTouch() {
        return this == BROKEN || this == POPPED;
    }

    /**
     * Checks if liquid can flow into this block.
     * <p>
     * Please note that this method only checks if the liquid can flow into this block,
     * it doesn't mean the block can contain the liquid source block.
     *
     * @return {@code true} if flow is allowed (NOREACTION), {@code false} otherwise
     */
    public boolean canLiquidFlowInto() {
        return this == NOREACTION;
    }
}
