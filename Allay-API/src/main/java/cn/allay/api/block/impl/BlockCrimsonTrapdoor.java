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
public interface BlockCrimsonTrapdoor extends Block {
    BlockType<BlockCrimsonTrapdoor> TYPE = BlockTypeBuilder
            .builder(BlockCrimsonTrapdoor.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_TRAPDOOR, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.OPEN_BIT,
                    VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
