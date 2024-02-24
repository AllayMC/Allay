package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWeatheredCopperBulbBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWeatheredCopperBulbBehaviorInitializer {
  static void init() {
    BlockTypes.WEATHERED_COPPER_BULB_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCopperBulbBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_COPPER_BULB)
            .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }
}
