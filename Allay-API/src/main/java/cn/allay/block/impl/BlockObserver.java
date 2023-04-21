package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockObserver extends Block {
    BlockType<BlockObserver> TYPE = BlockTypeBuilder
            .builder(BlockObserver.class)
            .vanillaBlock(VanillaBlockId.OBSERVER)
            .property(VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
}
