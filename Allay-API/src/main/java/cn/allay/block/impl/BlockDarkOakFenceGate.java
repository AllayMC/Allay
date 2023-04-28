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
public interface BlockDarkOakFenceGate extends Block {
    BlockType<BlockDarkOakFenceGate> TYPE = BlockTypeBuilder
            .builder(BlockDarkOakFenceGate.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE_GATE, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.IN_WALL_BIT,
                    VanillaBlockPropertyTypes.OPEN_BIT)
            .addBasicComponents()
            .build();
}
