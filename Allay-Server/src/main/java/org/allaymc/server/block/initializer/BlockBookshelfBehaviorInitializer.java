package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBookshelfBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBookshelfBehaviorInitializer {
  static void init() {
    BlockTypes.BOOKSHELF_TYPE = BlockTypeBuilder
            .builder(BlockBookshelfBehavior.class)
            .vanillaBlock(VanillaBlockId.BOOKSHELF)
            .build();
  }
}
