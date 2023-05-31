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
public interface BlockComposter extends Block {
    BlockType<BlockComposter> TYPE = BlockTypeBuilder
            .builder(BlockComposter.class)
            .vanillaBlock(VanillaBlockId.COMPOSTER, true)
            .withProperties(VanillaBlockPropertyTypes.COMPOSTER_FILL_LEVEL)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
