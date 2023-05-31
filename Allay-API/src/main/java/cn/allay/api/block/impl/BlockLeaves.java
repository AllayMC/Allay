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
public interface BlockLeaves extends Block {
    BlockType<BlockLeaves> TYPE = BlockTypeBuilder
            .builder(BlockLeaves.class)
            .vanillaBlock(VanillaBlockId.LEAVES, true)
            .withProperties(VanillaBlockPropertyTypes.OLD_LEAF_TYPE,
                    VanillaBlockPropertyTypes.PERSISTENT_BIT,
                    VanillaBlockPropertyTypes.UPDATE_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
