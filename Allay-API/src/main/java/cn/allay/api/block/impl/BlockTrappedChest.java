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
public interface BlockTrappedChest extends Block {
    BlockType<BlockTrappedChest> TYPE = BlockTypeBuilder
            .builder(BlockTrappedChest.class)
            .vanillaBlock(VanillaBlockId.TRAPPED_CHEST, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
