package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockJukeboxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockJukeboxBehaviorInitializer {
  static void init() {
    BlockTypes.JUKEBOX_TYPE = BlockTypeBuilder
            .builder(BlockJukeboxBehavior.class)
            .vanillaBlock(VanillaBlockId.JUKEBOX)
            .build();
  }
}
