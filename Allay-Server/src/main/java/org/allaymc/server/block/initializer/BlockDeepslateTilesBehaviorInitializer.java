package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDeepslateTilesBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateTilesBehaviorInitializer {
  static void init() {
    BlockTypes.DEEPSLATE_TILES_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateTilesBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_TILES)
            .build();
  }
}
