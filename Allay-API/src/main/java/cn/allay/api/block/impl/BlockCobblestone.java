package cn.allay.api.block.impl;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCobblestone extends Block {
    BlockType<BlockCobblestone> TYPE = BlockTypeBuilder
            .builder(BlockCobblestone.class)
            .vanillaBlock(VanillaBlockId.COBBLESTONE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
