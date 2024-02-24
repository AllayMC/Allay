package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSandBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSandBehaviorInitializer {
  static void init() {
    BlockTypes.SAND_TYPE = BlockTypeBuilder
            .builder(BlockSandBehavior.class)
            .vanillaBlock(VanillaBlockId.SAND)
            .setProperties(VanillaBlockPropertyTypes.SAND_TYPE)
            .build();
  }
}
