package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockOxidizedCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOxidizedCopperBehaviorInitializer {
  static void init() {
    BlockTypes.OXIDIZED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER)
            .build();
  }
}
