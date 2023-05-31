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
public interface BlockHardStainedGlass extends Block {
    BlockType<BlockHardStainedGlass> TYPE = BlockTypeBuilder
            .builder(BlockHardStainedGlass.class)
            .vanillaBlock(VanillaBlockId.HARD_STAINED_GLASS, true)
            .withProperties(VanillaBlockPropertyTypes.COLOR)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
