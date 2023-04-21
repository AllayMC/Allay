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
public interface BlockPointedDripstone extends Block {
    BlockType<BlockPointedDripstone> TYPE = BlockTypeBuilder
            .builder(BlockPointedDripstone.class)
            .vanillaBlock(VanillaBlockId.POINTED_DRIPSTONE)
            .property(VanillaBlockPropertyTypes.DRIPSTONE_THICKNESS,
                    VanillaBlockPropertyTypes.HANGING)
            .build();
}
