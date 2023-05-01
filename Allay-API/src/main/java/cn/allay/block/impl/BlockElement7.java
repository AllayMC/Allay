package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement7 extends Block {
    BlockType<BlockElement7> TYPE = BlockTypeBuilder
            .builder(BlockElement7.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_7, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
