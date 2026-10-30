package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code BlockStrippableComponent} resolves stripped block states and identifies
 * states that are already stripped. The server implementation selects a configured
 * stripped type and copies the input state's property values, so callers must supply
 * states compatible with that counterpart.
 *
 * @author IWareQ
 */
public interface BlockStrippableComponent extends BlockComponent {
    /**
     * Returns a stripped counterpart carrying the input state's property values. The
     * input must have properties compatible with the stripped type; the server lookup
     * can return {@code null} when those values do not identify a valid state.
     */
    BlockState getStrippedBlockState(BlockState unstrippedBlockState);

    /**
     * Returns whether the supplied state belongs to this component's stripped block
     * type.
     */
    boolean isStripped(BlockState blockState);
}
