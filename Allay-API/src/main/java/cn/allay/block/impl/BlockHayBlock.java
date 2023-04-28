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
public interface BlockHayBlock extends Block {
    BlockType<BlockHayBlock> TYPE = BlockTypeBuilder
            .builder(BlockHayBlock.class)
            .vanillaBlock(VanillaBlockId.HAY_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.DEPRECATED,
                    VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build();
}
