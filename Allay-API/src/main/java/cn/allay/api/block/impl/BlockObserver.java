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
public interface BlockObserver extends Block {
    BlockType<BlockObserver> TYPE = BlockTypeBuilder
            .builder(BlockObserver.class)
            .vanillaBlock(VanillaBlockId.OBSERVER, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.POWERED_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
