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
public interface BlockBoneBlock extends Block {
    BlockType<BlockBoneBlock> TYPE = BlockTypeBuilder
            .builder(BlockBoneBlock.class)
            .vanillaBlock(VanillaBlockId.BONE_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.DEPRECATED,
                    VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
