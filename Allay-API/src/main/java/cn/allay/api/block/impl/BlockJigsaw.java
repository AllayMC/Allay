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
public interface BlockJigsaw extends Block {
    BlockType<BlockJigsaw> TYPE = BlockTypeBuilder
            .builder(BlockJigsaw.class)
            .vanillaBlock(VanillaBlockId.JIGSAW, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.ROTATION)
            .addBasicComponents()
            .build();
}
