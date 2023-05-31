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
public interface BlockCrimsonFenceGate extends Block {
    BlockType<BlockCrimsonFenceGate> TYPE = BlockTypeBuilder
            .builder(BlockCrimsonFenceGate.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_FENCE_GATE, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.IN_WALL_BIT,
                    VanillaBlockPropertyTypes.OPEN_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
