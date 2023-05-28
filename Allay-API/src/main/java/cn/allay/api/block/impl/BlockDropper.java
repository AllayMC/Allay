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
public interface BlockDropper extends Block {
    BlockType<BlockDropper> TYPE = BlockTypeBuilder
            .builder(BlockDropper.class)
            .vanillaBlock(VanillaBlockId.DROPPER, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.TRIGGERED_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
