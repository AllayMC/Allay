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
public interface BlockReeds extends Block {
    BlockType<BlockReeds> TYPE = BlockTypeBuilder
            .builder(BlockReeds.class)
            .vanillaBlock(VanillaBlockId.REEDS)
            .property(VanillaBlockPropertyTypes.AGE)
            .build();
}
