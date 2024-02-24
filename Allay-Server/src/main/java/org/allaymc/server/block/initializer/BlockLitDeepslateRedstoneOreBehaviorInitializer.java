package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLitDeepslateRedstoneOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLitDeepslateRedstoneOreBehaviorInitializer {
  static void init() {
    BlockTypes.LIT_DEEPSLATE_REDSTONE_ORE_TYPE = BlockTypeBuilder
            .builder(BlockLitDeepslateRedstoneOreBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_DEEPSLATE_REDSTONE_ORE)
            .build();
  }
}
