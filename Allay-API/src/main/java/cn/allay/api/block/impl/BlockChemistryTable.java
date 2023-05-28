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
public interface BlockChemistryTable extends Block {
    BlockType<BlockChemistryTable> TYPE = BlockTypeBuilder
            .builder(BlockChemistryTable.class)
            .vanillaBlock(VanillaBlockId.CHEMISTRY_TABLE, true)
            .withProperties(VanillaBlockPropertyTypes.CHEMISTRY_TABLE_TYPE,
                    VanillaBlockPropertyTypes.DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
