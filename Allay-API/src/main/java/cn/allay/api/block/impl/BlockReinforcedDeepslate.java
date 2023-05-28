package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockReinforcedDeepslate extends Block {
    BlockType<BlockReinforcedDeepslate> TYPE = BlockTypeBuilder
            .builder(BlockReinforcedDeepslate.class)
            .vanillaBlock(VanillaBlockId.REINFORCED_DEEPSLATE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
