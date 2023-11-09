package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSmithingTableBehavior extends BlockBehavior {
  BlockType<BlockSmithingTableBehavior> SMITHING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockSmithingTableBehavior.class)
          .vanillaBlock(VanillaBlockId.SMITHING_TABLE)
          .build();
}
