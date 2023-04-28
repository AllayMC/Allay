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
public interface BlockLantern extends Block {
    BlockType<BlockLantern> TYPE = BlockTypeBuilder
            .builder(BlockLantern.class)
            .vanillaBlock(VanillaBlockId.LANTERN, true)
            .withProperties(VanillaBlockPropertyTypes.HANGING)
            .addBasicComponents()
            .build();
}
