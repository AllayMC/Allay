package org.allaymc.server.block.component.torch;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;

/**
 * @author IWareQ
 */
public class BlockColoredTorchBaseComponentImpl extends BlockTorchBaseComponentImpl {
    public BlockColoredTorchBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }
}
