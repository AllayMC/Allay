package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCopperBlock extends Block {
    BlockType<BlockCopperBlock> TYPE = BlockTypeBuilder
            .builder(BlockCopperBlock.class)
            .vanillaBlock(VanillaBlockId.COPPER_BLOCK, true)
            .addBasicComponents()
            .build();
}
