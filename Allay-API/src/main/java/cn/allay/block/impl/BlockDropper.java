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
public interface BlockDropper extends Block {
    BlockType<BlockDropper> TYPE = BlockTypeBuilder
            .builder(BlockDropper.class)
            .vanillaBlock(VanillaBlockId.DROPPER)
            .property(VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.TRIGGERED_BIT)
            .build();
}
