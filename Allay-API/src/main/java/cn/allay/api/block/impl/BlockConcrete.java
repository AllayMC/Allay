package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockConcrete extends Block {
    BlockType<BlockConcrete> TYPE = BlockTypeBuilder
            .builder(BlockConcrete.class)
            .vanillaBlock(VanillaBlockId.CONCRETE, true)
            .withProperties(VanillaBlockPropertyTypes.COLOR)
            .addBasicComponents()
            .build();
}
