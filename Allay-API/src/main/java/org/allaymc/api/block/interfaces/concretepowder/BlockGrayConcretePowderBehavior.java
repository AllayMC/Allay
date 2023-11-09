package org.allaymc.api.block.interfaces.concretepowder;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGrayConcretePowderBehavior extends BlockBehavior {
  BlockType<BlockGrayConcretePowderBehavior> GRAY_CONCRETE_POWDER_TYPE = BlockTypeBuilder
          .builder(BlockGrayConcretePowderBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_CONCRETE_POWDER)
          .build();
}
