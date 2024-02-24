package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockChemicalHeatBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChemicalHeatBehaviorInitializer {
  static void init() {
    BlockTypes.CHEMICAL_HEAT_TYPE = BlockTypeBuilder
            .builder(BlockChemicalHeatBehavior.class)
            .vanillaBlock(VanillaBlockId.CHEMICAL_HEAT)
            .build();
  }
}
