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
public interface BlockBambooMosaicDoubleSlab extends Block {
    BlockType<BlockBambooMosaicDoubleSlab> TYPE = BlockTypeBuilder
            .builder(BlockBambooMosaicDoubleSlab.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_DOUBLE_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
