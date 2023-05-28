package cn.allay.api.block.impl;

import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSuspiciousGravel extends Block {
    BlockType<BlockSuspiciousGravel> TYPE = BlockTypeBuilder
            .builder(BlockSuspiciousGravel.class)
            .vanillaBlock(VanillaBlockId.SUSPICIOUS_GRAVEL, true)
            .withProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS,
                    VanillaBlockPropertyTypes.HANGING)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
