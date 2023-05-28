package cn.allay.api.block.impl;

import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;

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
