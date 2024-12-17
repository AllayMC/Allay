package org.allaymc.api.block.component;

import org.allaymc.api.block.data.OxidationLevel;
import org.allaymc.api.block.type.BlockType;

/**
 * @author IWareQ
 */
public interface BlockOxidationComponent extends BlockComponent {
    /**
     * Retrieves the block with the specified oxidation level.
     *
     * @param oxidationLevel the oxidation level
     *
     * @return the block with the specified oxidation level
     */
    BlockType<?> getBlockWithOxidationLevel(OxidationLevel oxidationLevel);

    /**
     * Retrieves the current oxidation level of the block.
     *
     * @return the current oxidation level of the block
     */
    OxidationLevel getOxidationLevel();

    /**
     * Checks if the block is waxed.
     *
     * @return {@code true} if the block is waxed, {@code false} otherwise
     */
    boolean isWaxed();

    /**
     * Retrieves the block with the specified waxed state.
     *
     * @param waxed the waxed state
     *
     * @return the block with the specified waxed state
     */
    BlockType<?> getBlockWithWaxed(boolean waxed);
}
