package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockNoteblockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNoteblockBehaviorInitializer {
  static void init() {
    BlockTypes.NOTEBLOCK_TYPE = BlockTypeBuilder
            .builder(BlockNoteblockBehavior.class)
            .vanillaBlock(VanillaBlockId.NOTEBLOCK)
            .build();
  }
}
