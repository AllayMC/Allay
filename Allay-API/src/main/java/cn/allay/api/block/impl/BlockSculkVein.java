package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSculkVein extends Block {
    BlockType<BlockSculkVein> TYPE = BlockTypeBuilder
            .builder(BlockSculkVein.class)
            .vanillaBlock(VanillaBlockId.SCULK_VEIN, true)
            .withProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
