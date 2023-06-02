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
public interface BlockLitPumpkin extends Block {
    BlockType<BlockLitPumpkin> TYPE = BlockTypeBuilder
            .builder(BlockLitPumpkin.class)
            .vanillaBlock(VanillaBlockId.LIT_PUMPKIN, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION)
            .addBasicComponents()
            .build();
}
