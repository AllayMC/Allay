package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockJigsawBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockJigsawBehaviorInitializer {
  static void init() {
    BlockTypes.JIGSAW_TYPE = BlockTypeBuilder
            .builder(BlockJigsawBehavior.class)
            .vanillaBlock(VanillaBlockId.JIGSAW)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ROTATION)
            .build();
  }
}
