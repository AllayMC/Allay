package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockOxidizedCopperGrateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOxidizedCopperGrateBehaviorInitializer {
  static void init() {
    BlockTypes.OXIDIZED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER_GRATE)
            .build();
  }
}
