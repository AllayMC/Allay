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
public interface BlockSoulLantern extends Block {
    BlockType<BlockSoulLantern> TYPE = BlockTypeBuilder
            .builder(BlockSoulLantern.class)
            .vanillaBlock(VanillaBlockId.SOUL_LANTERN, true)
            .withProperties(VanillaBlockPropertyTypes.HANGING)
            .addBasicComponents()
            .build();
}
