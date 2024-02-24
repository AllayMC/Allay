package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCalciteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCalciteBehaviorInitializer {
  static void init() {
    BlockTypes.CALCITE_TYPE = BlockTypeBuilder
            .builder(BlockCalciteBehavior.class)
            .vanillaBlock(VanillaBlockId.CALCITE)
            .build();
  }
}
