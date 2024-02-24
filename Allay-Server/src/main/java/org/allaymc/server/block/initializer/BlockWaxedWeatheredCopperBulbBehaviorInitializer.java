package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWaxedWeatheredCopperBulbBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedWeatheredCopperBulbBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_WEATHERED_COPPER_BULB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCopperBulbBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER_BULB)
            .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }
}
