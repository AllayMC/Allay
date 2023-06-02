package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOchreFroglight extends Block {
    BlockType<BlockOchreFroglight> TYPE = BlockTypeBuilder
            .builder(BlockOchreFroglight.class)
            .vanillaBlock(VanillaBlockId.OCHRE_FROGLIGHT, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build();
}
