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
public interface BlockFarmland extends Block {
    BlockType<BlockFarmland> TYPE = BlockTypeBuilder
            .builder(BlockFarmland.class)
            .vanillaBlock(VanillaBlockId.FARMLAND, true)
            .withProperties(VanillaBlockPropertyTypes.MOISTURIZED_AMOUNT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
