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
public interface BlockBlackstoneDoubleSlab extends Block {
    BlockType<BlockBlackstoneDoubleSlab> TYPE = BlockTypeBuilder
            .builder(BlockBlackstoneDoubleSlab.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE_DOUBLE_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
