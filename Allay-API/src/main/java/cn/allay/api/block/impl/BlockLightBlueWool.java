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
public interface BlockLightBlueWool extends Block {
    BlockType<BlockLightBlueWool> TYPE = BlockTypeBuilder
            .builder(BlockLightBlueWool.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_WOOL, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
