package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockGlowLichenBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGlowLichenBehaviorInitializer {
  static void init() {
    BlockTypes.GLOW_LICHEN_TYPE = BlockTypeBuilder
            .builder(BlockGlowLichenBehavior.class)
            .vanillaBlock(VanillaBlockId.GLOW_LICHEN)
            .setProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
            .build();
  }
}
