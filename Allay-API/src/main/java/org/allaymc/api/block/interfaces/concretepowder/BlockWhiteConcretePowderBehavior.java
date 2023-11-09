package org.allaymc.api.block.interfaces.concretepowder;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWhiteConcretePowderBehavior extends BlockBehavior {
  BlockType<BlockWhiteConcretePowderBehavior> WHITE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
          .builder(BlockWhiteConcretePowderBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_CONCRETE_POWDER)
          .build();
}
