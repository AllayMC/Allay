package org.allaymc.api.block.interfaces.concrete;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCyanConcreteBehavior extends BlockBehavior {
  BlockType<BlockCyanConcreteBehavior> CYAN_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockCyanConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_CONCRETE)
          .build();
}
