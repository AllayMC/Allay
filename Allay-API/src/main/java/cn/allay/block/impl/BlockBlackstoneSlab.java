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
public interface BlockBlackstoneSlab extends Block {
    BlockType<BlockBlackstoneSlab> TYPE = BlockTypeBuilder
            .builder(BlockBlackstoneSlab.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE_SLAB)
            .property(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .build();
}
