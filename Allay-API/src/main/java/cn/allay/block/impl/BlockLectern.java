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
public interface BlockLectern extends Block {
    BlockType<BlockLectern> TYPE = BlockTypeBuilder
            .builder(BlockLectern.class)
            .vanillaBlock(VanillaBlockId.LECTERN, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.POWERED_BIT)
            .addBasicComponents()
            .build();
}
