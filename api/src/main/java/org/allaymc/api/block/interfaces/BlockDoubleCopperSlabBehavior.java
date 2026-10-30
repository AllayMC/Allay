package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.component.BlockOxidationComponent;

/**
 * {@code BlockDoubleCopperSlabBehavior} exposes general block operations and oxidation
 * controls for double copper slab blocks. The inherited component contracts provide
 * oxidation level, wax state, and corresponding block type lookups and slab counterpart
 * lookup, so variants share those operations through one behavior type.
 */
public interface BlockDoubleCopperSlabBehavior extends BlockDoubleSlabBehavior, BlockOxidationComponent {
}
