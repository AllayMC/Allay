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
public interface BlockCocoa extends Block {
    BlockType<BlockCocoa> TYPE = BlockTypeBuilder
            .builder(BlockCocoa.class)
            .vanillaBlock(VanillaBlockId.COCOA, true)
            .withProperties(VanillaBlockPropertyTypes.COCOA_AGE,
                    VanillaBlockPropertyTypes.DIRECTION)
            .addBasicComponents()
            .build();
}
