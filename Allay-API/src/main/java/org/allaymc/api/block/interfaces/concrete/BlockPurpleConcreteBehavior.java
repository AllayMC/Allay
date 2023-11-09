package org.allaymc.api.block.interfaces.concrete;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPurpleConcreteBehavior extends BlockBehavior {
  BlockType<BlockPurpleConcreteBehavior> PURPLE_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockPurpleConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_CONCRETE)
          .build();
}
