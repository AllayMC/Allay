package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWeatheredCopperGrateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWeatheredCopperGrateBehaviorInitializer {
  static void init() {
    BlockTypes.WEATHERED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_COPPER_GRATE)
            .build();
  }
}
