package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockReserved6 extends Block {
    BlockType<BlockReserved6> TYPE = BlockTypeBuilder
            .builder(BlockReserved6.class)
            .vanillaBlock(VanillaBlockId.RESERVED6, true)
            .addBasicComponents()
            .build();
}
