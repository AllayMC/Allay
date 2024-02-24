package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCartographyTableBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCartographyTableBehaviorInitializer {
  static void init() {
    BlockTypes.CARTOGRAPHY_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockCartographyTableBehavior.class)
            .vanillaBlock(VanillaBlockId.CARTOGRAPHY_TABLE)
            .build();
  }
}
