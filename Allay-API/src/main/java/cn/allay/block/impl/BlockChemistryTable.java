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
public interface BlockChemistryTable extends Block {
    BlockType<BlockChemistryTable> TYPE = BlockTypeBuilder
            .builder(BlockChemistryTable.class)
            .vanillaBlock(VanillaBlockId.CHEMISTRY_TABLE)
            .property(VanillaBlockPropertyTypes.CHEMISTRY_TABLE_TYPE,
                    VanillaBlockPropertyTypes.DIRECTION)
            .build();
}
