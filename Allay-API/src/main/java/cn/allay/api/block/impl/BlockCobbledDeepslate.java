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
public interface BlockCobbledDeepslate extends Block {
    BlockType<BlockCobbledDeepslate> TYPE = BlockTypeBuilder
            .builder(BlockCobbledDeepslate.class)
            .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
