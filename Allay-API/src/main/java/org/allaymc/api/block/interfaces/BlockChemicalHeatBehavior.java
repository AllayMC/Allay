package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockChemicalHeatBehavior extends BlockBehavior {
  BlockType<BlockChemicalHeatBehavior> CHEMICAL_HEAT_TYPE = BlockTypeBuilder
          .builder(BlockChemicalHeatBehavior.class)
          .vanillaBlock(VanillaBlockId.CHEMICAL_HEAT)
          .build();
}
