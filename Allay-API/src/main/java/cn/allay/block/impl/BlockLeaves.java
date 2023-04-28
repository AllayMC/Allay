package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

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
            .build();
}
