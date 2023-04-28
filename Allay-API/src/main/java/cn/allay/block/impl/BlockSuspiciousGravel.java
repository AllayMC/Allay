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
public interface BlockSuspiciousGravel extends Block {
    BlockType<BlockSuspiciousGravel> TYPE = BlockTypeBuilder
            .builder(BlockSuspiciousGravel.class)
            .vanillaBlock(VanillaBlockId.SUSPICIOUS_GRAVEL)
            .property(VanillaBlockPropertyTypes.BRUSHED_PROGRESS,
                    VanillaBlockPropertyTypes.HANGING)
            .build();
}
