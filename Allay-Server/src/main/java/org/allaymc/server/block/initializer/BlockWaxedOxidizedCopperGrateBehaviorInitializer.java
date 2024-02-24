package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWaxedOxidizedCopperGrateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedOxidizedCopperGrateBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_OXIDIZED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_COPPER_GRATE)
            .build();
  }
}
