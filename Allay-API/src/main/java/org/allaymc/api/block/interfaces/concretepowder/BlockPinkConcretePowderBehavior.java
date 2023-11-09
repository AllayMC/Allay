package org.allaymc.api.block.interfaces.concretepowder;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPinkConcretePowderBehavior extends BlockBehavior {
  BlockType<BlockPinkConcretePowderBehavior> PINK_CONCRETE_POWDER_TYPE = BlockTypeBuilder
          .builder(BlockPinkConcretePowderBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_CONCRETE_POWDER)
          .build();
}
