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
public interface BlockUnpoweredRepeater extends Block {
    BlockType<BlockUnpoweredRepeater> TYPE = BlockTypeBuilder
            .builder(BlockUnpoweredRepeater.class)
            .vanillaBlock(VanillaBlockId.UNPOWERED_REPEATER, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.REPEATER_DELAY)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
