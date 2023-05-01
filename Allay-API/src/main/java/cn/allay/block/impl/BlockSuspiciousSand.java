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
public interface BlockSuspiciousSand extends Block {
    BlockType<BlockSuspiciousSand> TYPE = BlockTypeBuilder
            .builder(BlockSuspiciousSand.class)
            .vanillaBlock(VanillaBlockId.SUSPICIOUS_SAND, true)
            .withProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS,
                    VanillaBlockPropertyTypes.HANGING)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
