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
public interface BlockDispenser extends Block {
    BlockType<BlockDispenser> TYPE = BlockTypeBuilder
            .builder(BlockDispenser.class)
            .vanillaBlock(VanillaBlockId.DISPENSER, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.TRIGGERED_BIT)
            .addBasicComponents()
            .build();
}
