package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLeaves2 extends Block {
    BlockType<BlockLeaves2> TYPE = BlockTypeBuilder
            .builder(BlockLeaves2.class)
            .vanillaBlock(VanillaBlockId.LEAVES2, true)
            .withProperties(VanillaBlockPropertyTypes.NEW_LEAF_TYPE,
                    VanillaBlockPropertyTypes.PERSISTENT_BIT,
                    VanillaBlockPropertyTypes.UPDATE_BIT)
            .addBasicComponents()
            .build();
}
