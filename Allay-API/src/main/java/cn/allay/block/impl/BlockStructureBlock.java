package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStructureBlock extends Block {
    BlockType<BlockStructureBlock> TYPE = BlockTypeBuilder
            .builder(BlockStructureBlock.class)
            .vanillaBlock(VanillaBlockId.STRUCTURE_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.STRUCTURE_BLOCK_TYPE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
