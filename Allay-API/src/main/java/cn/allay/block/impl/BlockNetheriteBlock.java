package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNetheriteBlock extends Block {
    BlockType<BlockNetheriteBlock> TYPE = BlockTypeBuilder
            .builder(BlockNetheriteBlock.class)
            .vanillaBlock(VanillaBlockId.NETHERITE_BLOCK, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
