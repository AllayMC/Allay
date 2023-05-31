package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSculkCatalyst extends Block {
    BlockType<BlockSculkCatalyst> TYPE = BlockTypeBuilder
            .builder(BlockSculkCatalyst.class)
            .vanillaBlock(VanillaBlockId.SCULK_CATALYST, true)
            .withProperties(VanillaBlockPropertyTypes.BLOOM)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
