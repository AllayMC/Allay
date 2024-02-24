package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockOxidizedCutCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOxidizedCutCopperBehaviorInitializer {
  static void init() {
    BlockTypes.OXIDIZED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER)
            .build();
  }
}
