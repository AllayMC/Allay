package org.allaymc.api.block.interfaces.carpet;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlueCarpetBehavior extends BlockBehavior {
  BlockType<BlockBlueCarpetBehavior> BLUE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockBlueCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_CARPET)
          .build();
}
