package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOrangeWool extends Block {
    BlockType<BlockOrangeWool> TYPE = BlockTypeBuilder
            .builder(BlockOrangeWool.class)
            .vanillaBlock(VanillaBlockId.ORANGE_WOOL, true)
            .addBasicComponents()
            .build();
}
