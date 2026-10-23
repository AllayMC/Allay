package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockType;

/**
 * {@code BlockInfestedBlockBaseComponent} exposes the ordinary block type imitated by
 * an infested block. The counterpart relationship allows drop handling to select the
 * imitated block's item representation while the inherited base component supplies
 * general block operations.
 *
 * @author IWareQ
 */
public interface BlockInfestedBlockBaseComponent extends BlockBaseComponent {
    /**
     * Returns the ordinary block type imitated by this infested block.
     */
    BlockType<?> getImitatedBlock();
}
