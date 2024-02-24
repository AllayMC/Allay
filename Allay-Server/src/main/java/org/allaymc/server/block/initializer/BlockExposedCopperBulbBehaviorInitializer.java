package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockExposedCopperBulbBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockExposedCopperBulbBehaviorInitializer {
  static void init() {
    BlockTypes.EXPOSED_COPPER_BULB_TYPE = BlockTypeBuilder
            .builder(BlockExposedCopperBulbBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_COPPER_BULB)
            .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }
}
