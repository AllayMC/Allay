package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockChiseledCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChiseledCopperBehaviorInitializer {
  static void init() {
    BlockTypes.CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_COPPER)
            .build();
  }
}
