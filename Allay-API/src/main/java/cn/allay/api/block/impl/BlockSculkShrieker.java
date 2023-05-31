package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSculkShrieker extends Block {
    BlockType<BlockSculkShrieker> TYPE = BlockTypeBuilder
            .builder(BlockSculkShrieker.class)
            .vanillaBlock(VanillaBlockId.SCULK_SHRIEKER, true)
            .withProperties(VanillaBlockPropertyTypes.ACTIVE,
                    VanillaBlockPropertyTypes.CAN_SUMMON)
            .addBasicComponents()
            .build();
}
