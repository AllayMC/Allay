package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.component.BlockOxidationComponent;

/**
 * {@code BlockCopperSlabBehavior} exposes general block operations and oxidation controls
 * for copper slab blocks. The inherited component contracts provide oxidation level, wax
 * state, and corresponding block type lookups and slab counterpart lookup, so variants
 * share those operations through one behavior type.
 */
public interface BlockCopperSlabBehavior extends BlockSlabBehavior, BlockOxidationComponent {
}
