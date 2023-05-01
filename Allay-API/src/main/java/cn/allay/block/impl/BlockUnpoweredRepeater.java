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
public interface BlockUnpoweredRepeater extends Block {
    BlockType<BlockUnpoweredRepeater> TYPE = BlockTypeBuilder
            .builder(BlockUnpoweredRepeater.class)
            .vanillaBlock(VanillaBlockId.UNPOWERED_REPEATER, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.REPEATER_DELAY)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
