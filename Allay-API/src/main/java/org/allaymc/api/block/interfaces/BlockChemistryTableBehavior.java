package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

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
