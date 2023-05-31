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
public interface BlockTnt extends Block {
    BlockType<BlockTnt> TYPE = BlockTypeBuilder
            .builder(BlockTnt.class)
            .vanillaBlock(VanillaBlockId.TNT, true)
            .withProperties(VanillaBlockPropertyTypes.ALLOW_UNDERWATER_BIT,
                    VanillaBlockPropertyTypes.EXPLODE_BIT)
            .addBasicComponents()
            .build();
}
