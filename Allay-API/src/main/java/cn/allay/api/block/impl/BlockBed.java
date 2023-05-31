package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBed extends Block {
    BlockType<BlockBed> TYPE = BlockTypeBuilder
            .builder(BlockBed.class)
            .vanillaBlock(VanillaBlockId.BED, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.HEAD_PIECE_BIT,
                    VanillaBlockPropertyTypes.OCCUPIED_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
