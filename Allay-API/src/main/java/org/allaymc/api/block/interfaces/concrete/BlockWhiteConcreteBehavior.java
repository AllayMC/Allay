package org.allaymc.api.block.interfaces.concrete;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWhiteConcreteBehavior extends BlockBehavior {
  BlockType<BlockWhiteConcreteBehavior> WHITE_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockWhiteConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_CONCRETE)
          .build();
}
