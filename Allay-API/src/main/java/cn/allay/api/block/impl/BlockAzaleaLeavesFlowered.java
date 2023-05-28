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
public interface BlockAzaleaLeavesFlowered extends Block {
    BlockType<BlockAzaleaLeavesFlowered> TYPE = BlockTypeBuilder
            .builder(BlockAzaleaLeavesFlowered.class)
            .vanillaBlock(VanillaBlockId.AZALEA_LEAVES_FLOWERED, true)
            .withProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT,
                    VanillaBlockPropertyTypes.UPDATE_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
