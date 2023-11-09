package org.allaymc.api.block.interfaces.concretepowder;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedConcretePowderBehavior extends BlockBehavior {
  BlockType<BlockRedConcretePowderBehavior> RED_CONCRETE_POWDER_TYPE = BlockTypeBuilder
          .builder(BlockRedConcretePowderBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_CONCRETE_POWDER)
          .build();
}
