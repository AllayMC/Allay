package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSweetBerryBushBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSweetBerryBushBehaviorInitializer {
  static void init() {
    BlockTypes.SWEET_BERRY_BUSH_TYPE = BlockTypeBuilder
            .builder(BlockSweetBerryBushBehavior.class)
            .vanillaBlock(VanillaBlockId.SWEET_BERRY_BUSH)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
  }
}
