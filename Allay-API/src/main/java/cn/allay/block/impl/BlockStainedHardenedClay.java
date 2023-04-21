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
public interface BlockStainedHardenedClay extends Block {
    BlockType<BlockStainedHardenedClay> TYPE = BlockTypeBuilder
            .builder(BlockStainedHardenedClay.class)
            .vanillaBlock(VanillaBlockId.STAINED_HARDENED_CLAY)
            .property(VanillaBlockPropertyTypes.COLOR)
            .build();
}
