package org.allaymc.server.block.initializer.planks;

import org.allaymc.api.block.interfaces.planks.BlockAcaciaPlanksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAcaciaPlanksBehaviorInitializer {
  static void init() {
    BlockTypes.ACACIA_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_PLANKS)
            .build();
  }
}
