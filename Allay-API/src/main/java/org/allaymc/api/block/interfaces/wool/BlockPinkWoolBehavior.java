package org.allaymc.api.block.interfaces.wool;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPinkWoolBehavior extends BlockBehavior {
  BlockType<BlockPinkWoolBehavior> PINK_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockPinkWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_WOOL)
          .build();
}
