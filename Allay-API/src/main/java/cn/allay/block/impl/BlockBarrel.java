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
public interface BlockBarrel extends Block {
    BlockType<BlockBarrel> TYPE = BlockTypeBuilder
            .builder(BlockBarrel.class)
            .vanillaBlock(VanillaBlockId.BARREL)
            .property(VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
}
