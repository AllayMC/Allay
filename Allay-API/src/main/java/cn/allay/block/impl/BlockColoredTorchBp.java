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
public interface BlockColoredTorchBp extends Block {
    BlockType<BlockColoredTorchBp> TYPE = BlockTypeBuilder
            .builder(BlockColoredTorchBp.class)
            .vanillaBlock(VanillaBlockId.COLORED_TORCH_BP)
            .property(VanillaBlockPropertyTypes.COLOR_BIT,
                    VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .build();
}
