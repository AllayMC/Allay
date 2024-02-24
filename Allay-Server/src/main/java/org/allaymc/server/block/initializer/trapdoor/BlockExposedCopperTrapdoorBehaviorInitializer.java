package org.allaymc.server.block.initializer.trapdoor;

import org.allaymc.api.block.interfaces.trapdoor.BlockExposedCopperTrapdoorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockExposedCopperTrapdoorBehaviorInitializer {
  static void init() {
    BlockTypes.EXPOSED_COPPER_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockExposedCopperTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_COPPER_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }
}
