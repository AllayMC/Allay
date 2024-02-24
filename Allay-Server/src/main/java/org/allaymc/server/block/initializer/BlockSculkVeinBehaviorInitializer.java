package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSculkVeinBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSculkVeinBehaviorInitializer {
  static void init() {
    BlockTypes.SCULK_VEIN_TYPE = BlockTypeBuilder
            .builder(BlockSculkVeinBehavior.class)
            .vanillaBlock(VanillaBlockId.SCULK_VEIN)
            .setProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
            .build();
  }
}
