package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLitRedstoneOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLitRedstoneOreBehaviorInitializer {
  static void init() {
    BlockTypes.LIT_REDSTONE_ORE_TYPE = BlockTypeBuilder
            .builder(BlockLitRedstoneOreBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_REDSTONE_ORE)
            .build();
  }
}
