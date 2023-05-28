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
public interface BlockKelp extends Block {
    BlockType<BlockKelp> TYPE = BlockTypeBuilder
            .builder(BlockKelp.class)
            .vanillaBlock(VanillaBlockId.KELP, true)
            .withProperties(VanillaBlockPropertyTypes.KELP_AGE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
