package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockFrameBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockFrameBehaviorInitializer {
  static void init() {
    BlockTypes.FRAME_TYPE = BlockTypeBuilder
            .builder(BlockFrameBehavior.class)
            .vanillaBlock(VanillaBlockId.FRAME)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ITEM_FRAME_MAP_BIT, VanillaBlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
            .build();
  }
}
