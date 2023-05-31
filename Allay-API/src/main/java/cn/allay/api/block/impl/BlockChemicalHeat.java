package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChemicalHeat extends Block {
    BlockType<BlockChemicalHeat> TYPE = BlockTypeBuilder
            .builder(BlockChemicalHeat.class)
            .vanillaBlock(VanillaBlockId.CHEMICAL_HEAT, true)
            .addBasicComponents()
            .build();
}
