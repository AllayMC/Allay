package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPackedMudBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPackedMudBehaviorInitializer {
  static void init() {
    BlockTypes.PACKED_MUD_TYPE = BlockTypeBuilder
            .builder(BlockPackedMudBehavior.class)
            .vanillaBlock(VanillaBlockId.PACKED_MUD)
            .build();
  }
}
