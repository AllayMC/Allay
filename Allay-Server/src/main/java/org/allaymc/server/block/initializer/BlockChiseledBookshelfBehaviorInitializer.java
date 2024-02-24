package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockChiseledBookshelfBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChiseledBookshelfBehaviorInitializer {
  static void init() {
    BlockTypes.CHISELED_BOOKSHELF_TYPE = BlockTypeBuilder
            .builder(BlockChiseledBookshelfBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_BOOKSHELF)
            .setProperties(VanillaBlockPropertyTypes.BOOKS_STORED, VanillaBlockPropertyTypes.DIRECTION)
            .build();
  }
}
