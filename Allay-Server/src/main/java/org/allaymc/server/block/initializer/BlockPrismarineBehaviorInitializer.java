package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPrismarineBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPrismarineBehaviorInitializer {
  static void init() {
    BlockTypes.PRISMARINE_TYPE = BlockTypeBuilder
            .builder(BlockPrismarineBehavior.class)
            .vanillaBlock(VanillaBlockId.PRISMARINE)
            .setProperties(VanillaBlockPropertyTypes.PRISMARINE_BLOCK_TYPE)
            .build();
  }
}
