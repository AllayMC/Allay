package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMonsterEgg extends Block {
    BlockType<BlockMonsterEgg> TYPE = BlockTypeBuilder
            .builder(BlockMonsterEgg.class)
            .vanillaBlock(VanillaBlockId.MONSTER_EGG, true)
            .withProperties(VanillaBlockPropertyTypes.MONSTER_EGG_STONE_TYPE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
