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
public interface BlockMagentaCandleCake extends Block {
    BlockType<BlockMagentaCandleCake> TYPE = BlockTypeBuilder
            .builder(BlockMagentaCandleCake.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_CANDLE_CAKE)
            .property(VanillaBlockPropertyTypes.LIT)
            .build();
}
