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
public interface BlockSweetBerryBush extends Block {
    BlockType<BlockSweetBerryBush> TYPE = BlockTypeBuilder
            .builder(BlockSweetBerryBush.class)
            .vanillaBlock(VanillaBlockId.SWEET_BERRY_BUSH, true)
            .withProperties(VanillaBlockPropertyTypes.GROWTH)
            .addBasicComponents()
            .build();
}
