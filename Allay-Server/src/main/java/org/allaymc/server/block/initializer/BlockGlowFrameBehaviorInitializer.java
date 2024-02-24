package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockGlowFrameBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGlowFrameBehaviorInitializer {
  static void init() {
    BlockTypes.GLOW_FRAME_TYPE = BlockTypeBuilder
            .builder(BlockGlowFrameBehavior.class)
            .vanillaBlock(VanillaBlockId.GLOW_FRAME)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ITEM_FRAME_MAP_BIT, VanillaBlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
            .build();
  }
}
