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
public interface BlockTnt extends Block {
    BlockType<BlockTnt> TYPE = BlockTypeBuilder
            .builder(BlockTnt.class)
            .vanillaBlock(VanillaBlockId.TNT, true)
            .withProperties(VanillaBlockPropertyTypes.ALLOW_UNDERWATER_BIT,
                    VanillaBlockPropertyTypes.EXPLODE_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
