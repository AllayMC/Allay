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
public interface BlockLever extends Block {
    BlockType<BlockLever> TYPE = BlockTypeBuilder
            .builder(BlockLever.class)
            .vanillaBlock(VanillaBlockId.LEVER, true)
            .withProperties(VanillaBlockPropertyTypes.LEVER_DIRECTION,
                    VanillaBlockPropertyTypes.OPEN_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
