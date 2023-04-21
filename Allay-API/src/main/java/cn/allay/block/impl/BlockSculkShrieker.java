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
public interface BlockSculkShrieker extends Block {
    BlockType<BlockSculkShrieker> TYPE = BlockTypeBuilder
            .builder(BlockSculkShrieker.class)
            .vanillaBlock(VanillaBlockId.SCULK_SHRIEKER)
            .property(VanillaBlockPropertyTypes.ACTIVE,
                    VanillaBlockPropertyTypes.CAN_SUMMON)
            .build();
}
