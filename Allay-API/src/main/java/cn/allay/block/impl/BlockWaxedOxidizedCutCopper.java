package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedOxidizedCutCopper extends Block {
    BlockType<BlockWaxedOxidizedCutCopper> TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCutCopper.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER)
            .build();
}
