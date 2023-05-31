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
public interface BlockPolishedBlackstoneSlab extends Block {
    BlockType<BlockPolishedBlackstoneSlab> TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneSlab.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
