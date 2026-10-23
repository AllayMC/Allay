package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockOxidationComponent;

/**
 * {@code BlockLightningRodBehavior} combines the contracts for a lightning rod block
 * with oxidation and waxing variants. The inherited block behavior handles world
 * operations, while the oxidation contract identifies corresponding rod types for
 * callers changing oxidation level or wax state.
 */
public interface BlockLightningRodBehavior extends BlockBehavior, BlockOxidationComponent {
}
