package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDriedKelpBlock extends Block {
    BlockType<BlockDriedKelpBlock> TYPE = BlockTypeBuilder
            .builder(BlockDriedKelpBlock.class)
            .vanillaBlock(VanillaBlockId.DRIED_KELP_BLOCK, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
