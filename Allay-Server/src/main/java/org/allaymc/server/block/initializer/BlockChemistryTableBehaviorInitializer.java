package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockChemistryTableBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChemistryTableBehaviorInitializer {
  static void init() {
    BlockTypes.CHEMISTRY_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockChemistryTableBehavior.class)
            .vanillaBlock(VanillaBlockId.CHEMISTRY_TABLE)
            .setProperties(VanillaBlockPropertyTypes.CHEMISTRY_TABLE_TYPE, VanillaBlockPropertyTypes.DIRECTION)
            .build();
  }
}
