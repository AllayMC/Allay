package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChiseledBookshelf extends Block {
    BlockType<BlockChiseledBookshelf> TYPE = BlockTypeBuilder
            .builder(BlockChiseledBookshelf.class)
            .vanillaBlock(VanillaBlockId.CHISELED_BOOKSHELF, true)
            .withProperties(VanillaBlockPropertyTypes.BOOKS_STORED,
                    VanillaBlockPropertyTypes.DIRECTION)
            .addBasicComponents()
            .build();
}
