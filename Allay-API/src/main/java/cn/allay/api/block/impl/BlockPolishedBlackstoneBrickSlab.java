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
public interface BlockPolishedBlackstoneBrickSlab extends Block {
    BlockType<BlockPolishedBlackstoneBrickSlab> TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneBrickSlab.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
