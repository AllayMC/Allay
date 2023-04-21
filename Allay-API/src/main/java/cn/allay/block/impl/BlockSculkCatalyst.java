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
public interface BlockSculkCatalyst extends Block {
    BlockType<BlockSculkCatalyst> TYPE = BlockTypeBuilder
            .builder(BlockSculkCatalyst.class)
            .vanillaBlock(VanillaBlockId.SCULK_CATALYST)
            .property(VanillaBlockPropertyTypes.BLOOM)
            .build();
}
