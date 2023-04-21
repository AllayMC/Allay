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
public interface BlockPoweredRepeater extends Block {
    BlockType<BlockPoweredRepeater> TYPE = BlockTypeBuilder
            .builder(BlockPoweredRepeater.class)
            .vanillaBlock(VanillaBlockId.POWERED_REPEATER)
            .property(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.REPEATER_DELAY)
            .build();
}
