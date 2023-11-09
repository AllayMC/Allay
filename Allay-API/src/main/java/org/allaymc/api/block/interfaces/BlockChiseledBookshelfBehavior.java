package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockChiseledBookshelfBehavior extends BlockBehavior {
  BlockType<BlockChiseledBookshelfBehavior> CHISELED_BOOKSHELF_TYPE = BlockTypeBuilder
          .builder(BlockChiseledBookshelfBehavior.class)
          .vanillaBlock(VanillaBlockId.CHISELED_BOOKSHELF)
          .setProperties(VanillaBlockPropertyTypes.BOOKS_STORED, VanillaBlockPropertyTypes.DIRECTION)
          .build();
}
