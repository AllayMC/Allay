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
public interface BlockQuartzBlock extends Block {
    BlockType<BlockQuartzBlock> TYPE = BlockTypeBuilder
            .builder(BlockQuartzBlock.class)
            .vanillaBlock(VanillaBlockId.QUARTZ_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.CHISEL_TYPE,
                    VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build();
}
