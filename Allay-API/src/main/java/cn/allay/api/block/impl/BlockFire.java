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
public interface BlockFire extends Block {
    BlockType<BlockFire> TYPE = BlockTypeBuilder
            .builder(BlockFire.class)
            .vanillaBlock(VanillaBlockId.FIRE, true)
            .withProperties(VanillaBlockPropertyTypes.AGE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
