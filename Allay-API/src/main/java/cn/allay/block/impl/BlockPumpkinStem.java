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
public interface BlockPumpkinStem extends Block {
    BlockType<BlockPumpkinStem> TYPE = BlockTypeBuilder
            .builder(BlockPumpkinStem.class)
            .vanillaBlock(VanillaBlockId.PUMPKIN_STEM)
            .property(VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.GROWTH)
            .build();
}
