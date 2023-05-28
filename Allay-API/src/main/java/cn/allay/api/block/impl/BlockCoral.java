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
public interface BlockCoral extends Block {
    BlockType<BlockCoral> TYPE = BlockTypeBuilder
            .builder(BlockCoral.class)
            .vanillaBlock(VanillaBlockId.CORAL, true)
            .withProperties(VanillaBlockPropertyTypes.CORAL_COLOR,
                    VanillaBlockPropertyTypes.DEAD_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
