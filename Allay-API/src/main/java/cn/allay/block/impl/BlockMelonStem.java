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
public interface BlockMelonStem extends Block {
    BlockType<BlockMelonStem> TYPE = BlockTypeBuilder
            .builder(BlockMelonStem.class)
            .vanillaBlock(VanillaBlockId.MELON_STEM)
            .property(VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.GROWTH)
            .build();
}
