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
public interface BlockSnowLayer extends Block {
    BlockType<BlockSnowLayer> TYPE = BlockTypeBuilder
            .builder(BlockSnowLayer.class)
            .vanillaBlock(VanillaBlockId.SNOW_LAYER, true)
            .withProperties(VanillaBlockPropertyTypes.COVERED_BIT,
                    VanillaBlockPropertyTypes.HEIGHT)
            .addBasicComponents()
            .build();
}
