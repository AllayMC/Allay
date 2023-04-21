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
public interface BlockBigDripleaf extends Block {
    BlockType<BlockBigDripleaf> TYPE = BlockTypeBuilder
            .builder(BlockBigDripleaf.class)
            .vanillaBlock(VanillaBlockId.BIG_DRIPLEAF)
            .property(VanillaBlockPropertyTypes.BIG_DRIPLEAF_HEAD,
                    VanillaBlockPropertyTypes.BIG_DRIPLEAF_TILT,
                    VanillaBlockPropertyTypes.DIRECTION)
            .build();
}
