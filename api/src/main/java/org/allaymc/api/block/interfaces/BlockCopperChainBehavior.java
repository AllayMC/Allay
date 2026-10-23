package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockOxidationComponent;

/**
 * {@code BlockCopperChainBehavior} exposes general block operations and oxidation controls
 * for copper chain blocks. The inherited component contracts provide oxidation level, wax
 * state, and corresponding block type lookups, so variants share those operations through
 * one behavior type.
 */
public interface BlockCopperChainBehavior extends BlockBehavior, BlockOxidationComponent {
}
