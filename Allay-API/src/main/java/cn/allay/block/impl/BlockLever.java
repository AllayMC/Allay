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
public interface BlockLever extends Block {
    BlockType<BlockLever> TYPE = BlockTypeBuilder
            .builder(BlockLever.class)
            .vanillaBlock(VanillaBlockId.LEVER, true)
            .withProperties(VanillaBlockPropertyTypes.LEVER_DIRECTION,
                    VanillaBlockPropertyTypes.OPEN_BIT)
            .addBasicComponents()
            .build();
}
