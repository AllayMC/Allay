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
public interface BlockBell extends Block {
    BlockType<BlockBell> TYPE = BlockTypeBuilder
            .builder(BlockBell.class)
            .vanillaBlock(VanillaBlockId.BELL, true)
            .withProperties(VanillaBlockPropertyTypes.ATTACHMENT,
                    VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.TOGGLE_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
