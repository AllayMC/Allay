package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockUndyedShulkerBox extends Block {
    BlockType<BlockUndyedShulkerBox> TYPE = BlockTypeBuilder
            .builder(BlockUndyedShulkerBox.class)
            .vanillaBlock(VanillaBlockId.UNDYED_SHULKER_BOX, true)
            .addBasicComponents()
            .build();
}
