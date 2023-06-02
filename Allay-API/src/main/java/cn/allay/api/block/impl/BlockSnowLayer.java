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
public interface BlockSnowLayer extends Block {
    BlockType<BlockSnowLayer> TYPE = BlockTypeBuilder
            .builder(BlockSnowLayer.class)
            .vanillaBlock(VanillaBlockId.SNOW_LAYER, true)
            .withProperties(VanillaBlockPropertyTypes.COVERED_BIT,
                    VanillaBlockPropertyTypes.HEIGHT)
            .addBasicComponents()
            .build();
}
