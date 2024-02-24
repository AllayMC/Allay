package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockAncientDebrisBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAncientDebrisBehaviorInitializer {
  static void init() {
    BlockTypes.ANCIENT_DEBRIS_TYPE = BlockTypeBuilder
            .builder(BlockAncientDebrisBehavior.class)
            .vanillaBlock(VanillaBlockId.ANCIENT_DEBRIS)
            .build();
  }
}
