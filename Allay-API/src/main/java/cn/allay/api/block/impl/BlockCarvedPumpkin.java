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
public interface BlockCarvedPumpkin extends Block {
    BlockType<BlockCarvedPumpkin> TYPE = BlockTypeBuilder
            .builder(BlockCarvedPumpkin.class)
            .vanillaBlock(VanillaBlockId.CARVED_PUMPKIN, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION)
            .addBasicComponents()
            .build();
}
