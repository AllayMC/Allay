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
public interface BlockBarrel extends Block {
    BlockType<BlockBarrel> TYPE = BlockTypeBuilder
            .builder(BlockBarrel.class)
            .vanillaBlock(VanillaBlockId.BARREL, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.OPEN_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
