package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockUnlitRedstoneTorch extends Block {
    BlockType<BlockUnlitRedstoneTorch> TYPE = BlockTypeBuilder
            .builder(BlockUnlitRedstoneTorch.class)
            .vanillaBlock(VanillaBlockId.UNLIT_REDSTONE_TORCH, true)
            .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
