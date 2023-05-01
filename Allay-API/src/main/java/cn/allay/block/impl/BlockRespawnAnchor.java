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
public interface BlockRespawnAnchor extends Block {
    BlockType<BlockRespawnAnchor> TYPE = BlockTypeBuilder
            .builder(BlockRespawnAnchor.class)
            .vanillaBlock(VanillaBlockId.RESPAWN_ANCHOR, true)
            .withProperties(VanillaBlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
