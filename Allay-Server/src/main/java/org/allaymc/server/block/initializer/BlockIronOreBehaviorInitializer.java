package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockIronOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockIronOreBehaviorInitializer {
  static void init() {
    BlockTypes.IRON_ORE_TYPE = BlockTypeBuilder
            .builder(BlockIronOreBehavior.class)
            .vanillaBlock(VanillaBlockId.IRON_ORE)
            .build();
  }
}
