package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockScaffoldingBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockScaffoldingBehaviorInitializer {
  static void init() {
    BlockTypes.SCAFFOLDING_TYPE = BlockTypeBuilder
            .builder(BlockScaffoldingBehavior.class)
            .vanillaBlock(VanillaBlockId.SCAFFOLDING)
            .setProperties(VanillaBlockPropertyTypes.STABILITY, VanillaBlockPropertyTypes.STABILITY_CHECK)
            .build();
  }
}
