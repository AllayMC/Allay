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
public interface BlockMonsterEgg extends Block {
    BlockType<BlockMonsterEgg> TYPE = BlockTypeBuilder
            .builder(BlockMonsterEgg.class)
            .vanillaBlock(VanillaBlockId.MONSTER_EGG, true)
            .withProperties(VanillaBlockPropertyTypes.MONSTER_EGG_STONE_TYPE)
            .addBasicComponents()
            .build();
}
