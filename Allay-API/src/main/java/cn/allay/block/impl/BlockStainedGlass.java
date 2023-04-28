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
public interface BlockStainedGlass extends Block {
    BlockType<BlockStainedGlass> TYPE = BlockTypeBuilder
            .builder(BlockStainedGlass.class)
            .vanillaBlock(VanillaBlockId.STAINED_GLASS, true)
            .withProperties(VanillaBlockPropertyTypes.COLOR)
            .addBasicComponents()
            .build();
}
