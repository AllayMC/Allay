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
public interface BlockKelp extends Block {
    BlockType<BlockKelp> TYPE = BlockTypeBuilder
            .builder(BlockKelp.class)
            .vanillaBlock(VanillaBlockId.KELP, true)
            .withProperties(VanillaBlockPropertyTypes.KELP_AGE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
