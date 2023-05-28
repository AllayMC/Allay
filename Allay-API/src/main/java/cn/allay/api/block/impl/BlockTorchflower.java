package cn.allay.api.block.impl;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTorchflower extends Block {
    BlockType<BlockTorchflower> TYPE = BlockTypeBuilder
            .builder(BlockTorchflower.class)
            .vanillaBlock(VanillaBlockId.TORCHFLOWER, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
