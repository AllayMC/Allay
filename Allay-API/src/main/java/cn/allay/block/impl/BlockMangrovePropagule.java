package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangrovePropagule extends Block {
    BlockType<BlockMangrovePropagule> TYPE = BlockTypeBuilder
            .builder(BlockMangrovePropagule.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_PROPAGULE, true)
            .withProperties(VanillaBlockPropertyTypes.HANGING,
                    VanillaBlockPropertyTypes.PROPAGULE_STAGE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
