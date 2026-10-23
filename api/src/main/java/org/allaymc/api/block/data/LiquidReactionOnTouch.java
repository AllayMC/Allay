package org.allaymc.api.block.data;

/**
 * {@code LiquidReactionOnTouch} describes how a block responds when flowing liquid
 * reaches its position. The constants distinguish replacement from coexistence or
 * blocked flow; source-liquid containment remains a separate decision represented
 * by {@link BlockStateData#canContainLiquidSource}.
 *
 * @author daoge_cmd
 */
public enum LiquidReactionOnTouch {
    /**
     * Removal without item drops.
     */
    BROKEN,
    /**
     * Removal with block drops.
     */
    POPPED,
    /**
     * {@code BLOCKING} prevents flowing liquid from entering the block. Source
     * containment still depends separately on {@link
     * BlockStateData#canContainLiquidSource}, so blocking flow does not itself
     * forbid waterlogging.
     */
    BLOCKING,
    /**
     * Unchanged block allowing flow.
     */
    NOREACTION;

    /**
     * Returns whether this reaction removes the touched block, with {@code BROKEN}
     * and {@code POPPED} selecting removal.
     */
    public boolean shouldRemoveOnTouch() {
        return this == BROKEN || this == POPPED;
    }

    /**
     * Returns whether this reaction permits flow without removing the block. Only
     * {@code NOREACTION} returns {@code true}; source-liquid containment and
     * replacement reactions must be considered separately by liquid-placement
     * callers.
     */
    public boolean canLiquidFlowInto() {
        return this == NOREACTION;
    }
}
