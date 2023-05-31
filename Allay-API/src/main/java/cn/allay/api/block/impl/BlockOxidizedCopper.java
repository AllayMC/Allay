package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOxidizedCopper extends Block {
    BlockType<BlockOxidizedCopper> TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCopper.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER, true)
            .addBasicComponents()
            .build();
}
