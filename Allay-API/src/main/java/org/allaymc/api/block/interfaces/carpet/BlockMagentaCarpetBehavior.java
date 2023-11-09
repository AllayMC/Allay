package org.allaymc.api.block.interfaces.carpet;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMagentaCarpetBehavior extends BlockBehavior {
  BlockType<BlockMagentaCarpetBehavior> MAGENTA_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockMagentaCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CARPET)
          .build();
}
