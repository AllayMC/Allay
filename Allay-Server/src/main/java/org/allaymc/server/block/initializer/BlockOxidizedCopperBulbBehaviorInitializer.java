package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockOxidizedCopperBulbBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOxidizedCopperBulbBehaviorInitializer {
  static void init() {
    BlockTypes.OXIDIZED_COPPER_BULB_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCopperBulbBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER_BULB)
            .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }
}
