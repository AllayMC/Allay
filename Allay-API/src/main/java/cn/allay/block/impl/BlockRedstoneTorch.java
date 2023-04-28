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
public interface BlockRedstoneTorch extends Block {
    BlockType<BlockRedstoneTorch> TYPE = BlockTypeBuilder
            .builder(BlockRedstoneTorch.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_TORCH, true)
            .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
