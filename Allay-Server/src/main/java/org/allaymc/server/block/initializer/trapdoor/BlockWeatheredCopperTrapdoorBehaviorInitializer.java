package org.allaymc.server.block.initializer.trapdoor;

import org.allaymc.api.block.interfaces.trapdoor.BlockWeatheredCopperTrapdoorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWeatheredCopperTrapdoorBehaviorInitializer {
  static void init() {
    BlockTypes.WEATHERED_COPPER_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCopperTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_COPPER_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }
}
