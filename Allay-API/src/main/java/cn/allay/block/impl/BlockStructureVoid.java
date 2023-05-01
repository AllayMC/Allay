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
public interface BlockStructureVoid extends Block {
    BlockType<BlockStructureVoid> TYPE = BlockTypeBuilder
            .builder(BlockStructureVoid.class)
            .vanillaBlock(VanillaBlockId.STRUCTURE_VOID, true)
            .withProperties(VanillaBlockPropertyTypes.STRUCTURE_VOID_TYPE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
