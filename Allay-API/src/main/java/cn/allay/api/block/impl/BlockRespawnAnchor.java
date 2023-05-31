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
public interface BlockRespawnAnchor extends Block {
    BlockType<BlockRespawnAnchor> TYPE = BlockTypeBuilder
            .builder(BlockRespawnAnchor.class)
            .vanillaBlock(VanillaBlockId.RESPAWN_ANCHOR, true)
            .withProperties(VanillaBlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
