package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

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
