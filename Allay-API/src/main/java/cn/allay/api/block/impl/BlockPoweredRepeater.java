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
public interface BlockPoweredRepeater extends Block {
    BlockType<BlockPoweredRepeater> TYPE = BlockTypeBuilder
            .builder(BlockPoweredRepeater.class)
            .vanillaBlock(VanillaBlockId.POWERED_REPEATER, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.REPEATER_DELAY)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
