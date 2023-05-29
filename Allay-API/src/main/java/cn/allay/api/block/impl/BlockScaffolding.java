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
public interface BlockScaffolding extends Block {
    BlockType<BlockScaffolding> TYPE = BlockTypeBuilder
            .builder(BlockScaffolding.class)
            .vanillaBlock(VanillaBlockId.SCAFFOLDING, true)
            .withProperties(VanillaBlockPropertyTypes.STABILITY,
                    VanillaBlockPropertyTypes.STABILITY_CHECK)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
