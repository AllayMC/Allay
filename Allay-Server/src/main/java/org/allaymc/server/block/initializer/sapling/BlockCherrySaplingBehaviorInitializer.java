package org.allaymc.server.block.initializer.sapling;

import org.allaymc.api.block.interfaces.sapling.BlockCherrySaplingBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherrySaplingBehaviorInitializer {
  static void init() {
    BlockTypes.CHERRY_SAPLING_TYPE = BlockTypeBuilder
            .builder(BlockCherrySaplingBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_SAPLING)
            .setProperties(VanillaBlockPropertyTypes.AGE_BIT)
            .build();
  }
}
