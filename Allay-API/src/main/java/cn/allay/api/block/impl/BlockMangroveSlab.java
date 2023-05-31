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
public interface BlockMangroveSlab extends Block {
    BlockType<BlockMangroveSlab> TYPE = BlockTypeBuilder
            .builder(BlockMangroveSlab.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
