package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCake extends Block {
    BlockType<BlockCake> TYPE = BlockTypeBuilder
            .builder(BlockCake.class)
            .vanillaBlock(VanillaBlockId.CAKE, true)
            .withProperties(VanillaBlockPropertyTypes.BITE_COUNTER)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
