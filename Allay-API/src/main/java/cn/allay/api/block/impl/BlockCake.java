package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCake extends Block {
    BlockType<BlockCake> TYPE = BlockTypeBuilder
            .builder(BlockCake.class)
            .vanillaBlock(VanillaBlockId.CAKE, true)
            .withProperties(VanillaBlockPropertyTypes.BITE_COUNTER)
            .addBasicComponents()
            .build();
}
