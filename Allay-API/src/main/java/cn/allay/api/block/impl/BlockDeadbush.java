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
public interface BlockDeadbush extends Block {
    BlockType<BlockDeadbush> TYPE = BlockTypeBuilder
            .builder(BlockDeadbush.class)
            .vanillaBlock(VanillaBlockId.DEADBUSH, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
