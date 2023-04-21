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
public interface BlockMuddyMangroveRoots extends Block {
    BlockType<BlockMuddyMangroveRoots> TYPE = BlockTypeBuilder
            .builder(BlockMuddyMangroveRoots.class)
            .vanillaBlock(VanillaBlockId.MUDDY_MANGROVE_ROOTS)
            .property(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
