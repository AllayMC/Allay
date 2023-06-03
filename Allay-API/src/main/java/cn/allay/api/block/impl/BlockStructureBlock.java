package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStructureBlock extends Block {
    BlockType<BlockStructureBlock> TYPE = BlockTypeBuilder
            .builder(BlockStructureBlock.class)
            .vanillaBlock(VanillaBlockId.STRUCTURE_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.STRUCTURE_BLOCK_TYPE)
            .addBasicComponents()
            .build();
}
