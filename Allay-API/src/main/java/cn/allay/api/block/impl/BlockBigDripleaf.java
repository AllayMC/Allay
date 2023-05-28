package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBigDripleaf extends Block {
    BlockType<BlockBigDripleaf> TYPE = BlockTypeBuilder
            .builder(BlockBigDripleaf.class)
            .vanillaBlock(VanillaBlockId.BIG_DRIPLEAF, true)
            .withProperties(VanillaBlockPropertyTypes.BIG_DRIPLEAF_HEAD,
                    VanillaBlockPropertyTypes.BIG_DRIPLEAF_TILT,
                    VanillaBlockPropertyTypes.DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
