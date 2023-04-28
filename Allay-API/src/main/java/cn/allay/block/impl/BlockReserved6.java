package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockReserved6 extends Block {
    BlockType<BlockReserved6> TYPE = BlockTypeBuilder
            .builder(BlockReserved6.class)
            .vanillaBlock(VanillaBlockId.RESERVED6, true)
            .addBasicComponents()
            .build();
}
