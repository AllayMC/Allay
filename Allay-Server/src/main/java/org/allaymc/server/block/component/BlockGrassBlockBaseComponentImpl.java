package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;

/**
 * Allay Project 2024/6/15
 *
 * @author daoge_cmd
 */
public class BlockGrassBlockBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockGrassBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }
}
