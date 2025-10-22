package org.allaymc.api.block.component;

import org.allaymc.api.block.data.OxidationLevel;
import org.allaymc.api.block.type.BlockType;

/**
 * BlockOxidationComponent is implemented by all blocks that can be oxidized.
 *
 * @author IWareQ
 */
public interface BlockOxidationComponent extends BlockComponent {
    /**
     * Gets the block with the specified oxidation level.
     *
     * @param oxidationLevel the {@link OxidationLevel}
     * @return the corresponding {@link BlockType}
     */
    BlockType<? extends BlockOxidationComponent> getBlockWithOxidationLevel(OxidationLevel oxidationLevel);

    /**
     * Checks if the block can oxidize further.
     *
     * @return {@code true} if oxidizable, {@code false} otherwise
     */
    default boolean canOxidate() {
        return this.getOxidationLevel() != OxidationLevel.OXIDIZED && !this.isWaxed();
    }

    /**
     * Gets the current oxidation level of the block.
     *
     * @return the current {@link OxidationLevel}
     */
    OxidationLevel getOxidationLevel();

    /**
     * Checks if the block is waxed.
     *
     * @return {@code true} if waxed, {@code false} otherwise
     */
    boolean isWaxed();

    /**
     * Gets the block with the specified waxed state.
     *
     * @param waxed the waxed state
     * @return the corresponding {@link BlockType}
     */
    BlockType<? extends BlockOxidationComponent> getBlockWithWaxed(boolean waxed);
}
