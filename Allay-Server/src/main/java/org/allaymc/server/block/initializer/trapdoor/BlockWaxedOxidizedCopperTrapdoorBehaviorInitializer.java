package org.allaymc.server.block.initializer.trapdoor;

import org.allaymc.api.block.interfaces.trapdoor.BlockWaxedOxidizedCopperTrapdoorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedOxidizedCopperTrapdoorBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCopperTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_COPPER_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }
}
