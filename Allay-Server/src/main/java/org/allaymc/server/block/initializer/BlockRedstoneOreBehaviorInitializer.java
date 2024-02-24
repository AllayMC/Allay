package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRedstoneOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedstoneOreBehaviorInitializer {
  static void init() {
    BlockTypes.REDSTONE_ORE_TYPE = BlockTypeBuilder
            .builder(BlockRedstoneOreBehavior.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_ORE)
            .build();
  }
}
