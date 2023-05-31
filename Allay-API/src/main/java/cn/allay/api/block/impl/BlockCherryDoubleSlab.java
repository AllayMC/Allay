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
public interface BlockCherryDoubleSlab extends Block {
    BlockType<BlockCherryDoubleSlab> TYPE = BlockTypeBuilder
            .builder(BlockCherryDoubleSlab.class)
            .vanillaBlock(VanillaBlockId.CHERRY_DOUBLE_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
