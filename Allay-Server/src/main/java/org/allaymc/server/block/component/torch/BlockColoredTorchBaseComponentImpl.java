package org.allaymc.server.block.component.torch;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.annotation.RequireBlockProperty;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;

/**
 * Allay Project 25/06/2024
 *
 * @author IWareQ
 */
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "color_bit")
public class BlockColoredTorchBaseComponentImpl extends BlockTorchBaseComponentImpl {
    public BlockColoredTorchBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }
}
