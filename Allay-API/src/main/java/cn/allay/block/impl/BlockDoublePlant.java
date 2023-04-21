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
public interface BlockDoublePlant extends Block {
    BlockType<BlockDoublePlant> TYPE = BlockTypeBuilder
            .builder(BlockDoublePlant.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_PLANT)
            .property(VanillaBlockPropertyTypes.DOUBLE_PLANT_TYPE,
                    VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
}
