package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSoulLanternBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSoulLanternBehaviorInitializer {
  static void init() {
    BlockTypes.SOUL_LANTERN_TYPE = BlockTypeBuilder
            .builder(BlockSoulLanternBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_LANTERN)
            .setProperties(VanillaBlockPropertyTypes.HANGING)
            .build();
  }
}
