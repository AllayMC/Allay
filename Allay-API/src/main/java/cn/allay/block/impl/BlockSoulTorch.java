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
public interface BlockSoulTorch extends Block {
    BlockType<BlockSoulTorch> TYPE = BlockTypeBuilder
            .builder(BlockSoulTorch.class)
            .vanillaBlock(VanillaBlockId.SOUL_TORCH, true)
            .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
