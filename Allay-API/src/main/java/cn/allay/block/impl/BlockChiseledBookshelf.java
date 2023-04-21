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
public interface BlockChiseledBookshelf extends Block {
    BlockType<BlockChiseledBookshelf> TYPE = BlockTypeBuilder
            .builder(BlockChiseledBookshelf.class)
            .vanillaBlock(VanillaBlockId.CHISELED_BOOKSHELF)
            .property(VanillaBlockPropertyTypes.BOOKS_STORED,
                    VanillaBlockPropertyTypes.DIRECTION)
            .build();
}
