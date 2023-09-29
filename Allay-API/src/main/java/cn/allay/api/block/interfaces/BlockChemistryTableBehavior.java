package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockChemistryTableBehavior extends BlockBehavior {
  BlockType<BlockChemistryTableBehavior> CHEMISTRY_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockChemistryTableBehavior.class)
          .vanillaBlock(VanillaBlockId.CHEMISTRY_TABLE)
          .setProperties(VanillaBlockPropertyTypes.CHEMISTRY_TABLE_TYPE, VanillaBlockPropertyTypes.DIRECTION)
          .build();
}
