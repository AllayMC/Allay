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
public interface BlockPolishedBlackstoneBrickSlab extends Block {
    BlockType<BlockPolishedBlackstoneBrickSlab> TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneBrickSlab.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
