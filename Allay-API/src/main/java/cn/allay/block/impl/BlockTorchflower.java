package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTorchflower extends Block {
    BlockType<BlockTorchflower> TYPE = BlockTypeBuilder
            .builder(BlockTorchflower.class)
            .vanillaBlock(VanillaBlockId.TORCHFLOWER, true)
            .addBasicComponents()
            .build();
}
