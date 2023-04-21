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
public interface BlockKelp extends Block {
    BlockType<BlockKelp> TYPE = BlockTypeBuilder
            .builder(BlockKelp.class)
            .vanillaBlock(VanillaBlockId.KELP)
            .property(VanillaBlockPropertyTypes.KELP_AGE)
            .build();
}
