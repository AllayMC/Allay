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
public interface BlockCherryTrapdoor extends Block {
    BlockType<BlockCherryTrapdoor> TYPE = BlockTypeBuilder
            .builder(BlockCherryTrapdoor.class)
            .vanillaBlock(VanillaBlockId.CHERRY_TRAPDOOR, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.OPEN_BIT,
                    VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .addBasicComponents()
            .build();
}
