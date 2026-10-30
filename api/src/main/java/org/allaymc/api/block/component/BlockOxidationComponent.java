package org.allaymc.api.block.component;

import org.allaymc.api.block.data.OxidationLevel;
import org.allaymc.api.block.type.BlockType;

/**
 * {@code BlockOxidationComponent} exposes a copper block's oxidation level, wax
 * state, and corresponding block types. Oxidation lookup preserves the current wax
 * state, wax lookup preserves the current oxidation level, and the default
 * eligibility check excludes waxed or fully oxidized variants.
 *
 * @author IWareQ
 */
public interface BlockOxidationComponent extends BlockComponent {
    /**
     * Returns the corresponding block type at the requested oxidation level. The
     * lookup retains this component's wax state, allowing callers to change oxidation
     * without choosing a different waxed variant.
     */
    BlockType<? extends BlockOxidationComponent> getBlockWithOxidationLevel(OxidationLevel oxidationLevel);

    /**
     * Returns whether this block can advance to another oxidation level. The default
     * permits advancement only when this component is unwaxed and its level differs
     * from {@code OxidationLevel.OXIDIZED}.
     */
    default boolean canOxidate() {
        return this.getOxidationLevel() != OxidationLevel.OXIDIZED && !this.isWaxed();
    }

    /**
     * Returns the oxidation level represented by this block component.
     */
    OxidationLevel getOxidationLevel();

    /**
     * Returns whether this block component represents a waxed variant.
     */
    boolean isWaxed();

    /**
     * Returns the corresponding block type with the requested wax state. The lookup
     * retains this component's oxidation level, allowing callers to wax or unwax a
     * block without changing its oxidation.
     */
    BlockType<? extends BlockOxidationComponent> getBlockWithWaxed(boolean waxed);
}
