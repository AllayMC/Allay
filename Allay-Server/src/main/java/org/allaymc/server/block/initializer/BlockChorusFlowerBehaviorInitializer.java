package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockChorusFlowerBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChorusFlowerBehaviorInitializer {
  static void init() {
    BlockTypes.CHORUS_FLOWER_TYPE = BlockTypeBuilder
            .builder(BlockChorusFlowerBehavior.class)
            .vanillaBlock(VanillaBlockId.CHORUS_FLOWER)
            .setProperties(VanillaBlockPropertyTypes.AGE_6)
            .build();
  }
}
