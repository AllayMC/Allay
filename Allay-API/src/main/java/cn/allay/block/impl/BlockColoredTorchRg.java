package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockColoredTorchRg extends Block {
    BlockType<BlockColoredTorchRg> TYPE = BlockTypeBuilder
            .builder(BlockColoredTorchRg.class)
            .vanillaBlock(VanillaBlockId.COLORED_TORCH_RG, true)
            .withProperties(VanillaBlockPropertyTypes.COLOR_BIT,
                    VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
