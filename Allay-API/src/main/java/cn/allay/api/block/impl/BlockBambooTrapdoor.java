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
public interface BlockBambooTrapdoor extends Block {
    BlockType<BlockBambooTrapdoor> TYPE = BlockTypeBuilder
            .builder(BlockBambooTrapdoor.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_TRAPDOOR, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.OPEN_BIT,
                    VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
