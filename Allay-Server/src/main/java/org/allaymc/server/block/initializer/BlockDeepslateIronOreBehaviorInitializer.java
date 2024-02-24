package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDeepslateIronOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateIronOreBehaviorInitializer {
  static void init() {
    BlockTypes.DEEPSLATE_IRON_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateIronOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_IRON_ORE)
            .build();
  }
}
