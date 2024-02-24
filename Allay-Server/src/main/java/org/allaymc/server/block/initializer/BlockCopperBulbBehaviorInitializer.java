package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCopperBulbBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCopperBulbBehaviorInitializer {
  static void init() {
    BlockTypes.COPPER_BULB_TYPE = BlockTypeBuilder
            .builder(BlockCopperBulbBehavior.class)
            .vanillaBlock(VanillaBlockId.COPPER_BULB)
            .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }
}
