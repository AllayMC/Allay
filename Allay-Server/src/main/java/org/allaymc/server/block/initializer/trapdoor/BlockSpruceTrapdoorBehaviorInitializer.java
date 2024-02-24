package org.allaymc.server.block.initializer.trapdoor;

import org.allaymc.api.block.interfaces.trapdoor.BlockSpruceTrapdoorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSpruceTrapdoorBehaviorInitializer {
  static void init() {
    BlockTypes.SPRUCE_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockSpruceTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }
}
