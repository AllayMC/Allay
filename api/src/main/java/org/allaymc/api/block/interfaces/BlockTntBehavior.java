package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockTntBaseComponent;

/**
 * {@code BlockTntBehavior} exposes general block operations and priming operations for TNT
 * blocks. The inherited priming contract creates explosive entities at supplied positions
 * and accepts either an explicit fuse duration in ticks or the component default.
 */
public interface BlockTntBehavior extends BlockBehavior, BlockTntBaseComponent {
}
