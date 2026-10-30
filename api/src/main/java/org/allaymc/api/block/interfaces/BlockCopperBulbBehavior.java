package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockOxidationComponent;

/**
 * {@code BlockCopperBulbBehavior} combines the contracts for a copper bulb block with
 * oxidation and waxing variants. The inherited block behavior handles world operations,
 * while the oxidation contract identifies corresponding bulb types for callers changing
 * oxidation level or wax state.
 */
public interface BlockCopperBulbBehavior extends BlockBehavior, BlockOxidationComponent {
}
