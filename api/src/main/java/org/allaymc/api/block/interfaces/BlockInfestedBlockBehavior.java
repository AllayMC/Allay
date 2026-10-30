package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockInfestedBlockBaseComponent;

/**
 * {@code BlockInfestedBlockBehavior} exposes general block operations and the normal block
 * type imitated by an infested block. The inherited component contract identifies that
 * counterpart independently of the infested variant, allowing callers to resolve its
 * ordinary block appearance.
 */
public interface BlockInfestedBlockBehavior extends BlockBehavior, BlockInfestedBlockBaseComponent {
}
