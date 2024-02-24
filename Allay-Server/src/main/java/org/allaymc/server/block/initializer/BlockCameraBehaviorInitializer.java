package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCameraBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCameraBehaviorInitializer {
  static void init() {
    BlockTypes.CAMERA_TYPE = BlockTypeBuilder
            .builder(BlockCameraBehavior.class)
            .vanillaBlock(VanillaBlockId.CAMERA)
            .build();
  }
}
