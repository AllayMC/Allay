package org.allaymc.api.block.interfaces.carpet;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBrownCarpetBehavior extends BlockBehavior {
  BlockType<BlockBrownCarpetBehavior> BROWN_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockBrownCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_CARPET)
          .build();
}
