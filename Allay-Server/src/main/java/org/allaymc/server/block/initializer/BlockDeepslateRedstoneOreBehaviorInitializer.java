package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDeepslateRedstoneOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateRedstoneOreBehaviorInitializer {
  static void init() {
    BlockTypes.DEEPSLATE_REDSTONE_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateRedstoneOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_REDSTONE_ORE)
            .build();
  }
}
