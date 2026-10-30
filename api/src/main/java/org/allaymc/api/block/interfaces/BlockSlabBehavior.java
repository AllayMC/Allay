package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockSlabBaseComponent;

/**
 * {@code BlockSlabBehavior} exposes general block operations and the corresponding double
 * slab block type. The inherited slab component contract supplies that counterpart lookup,
 * so placement and drop handling can relate slab variants through the behavior interface.
 */
public interface BlockSlabBehavior extends BlockBehavior, BlockSlabBaseComponent {
}
