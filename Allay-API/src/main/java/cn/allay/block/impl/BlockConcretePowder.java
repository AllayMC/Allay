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
public interface BlockConcretePowder extends Block {
    BlockType<BlockConcretePowder> TYPE = BlockTypeBuilder
            .builder(BlockConcretePowder.class)
            .vanillaBlock(VanillaBlockId.CONCRETE_POWDER, true)
            .withProperties(VanillaBlockPropertyTypes.COLOR)
            .addBasicComponents()
            .build();
}
