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
public interface BlockUnderwaterTorch extends Block {
    BlockType<BlockUnderwaterTorch> TYPE = BlockTypeBuilder
            .builder(BlockUnderwaterTorch.class)
            .vanillaBlock(VanillaBlockId.UNDERWATER_TORCH, true)
            .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
