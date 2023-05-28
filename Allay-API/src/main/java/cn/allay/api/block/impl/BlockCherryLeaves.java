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
public interface BlockCherryLeaves extends Block {
    BlockType<BlockCherryLeaves> TYPE = BlockTypeBuilder
            .builder(BlockCherryLeaves.class)
            .vanillaBlock(VanillaBlockId.CHERRY_LEAVES, true)
            .withProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT,
                    VanillaBlockPropertyTypes.UPDATE_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
