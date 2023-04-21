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
public interface BlockComposter extends Block {
    BlockType<BlockComposter> TYPE = BlockTypeBuilder
            .builder(BlockComposter.class)
            .vanillaBlock(VanillaBlockId.COMPOSTER)
            .property(VanillaBlockPropertyTypes.COMPOSTER_FILL_LEVEL)
            .build();
}
