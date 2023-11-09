package org.allaymc.api.block.interfaces.concrete;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGreenConcreteBehavior extends BlockBehavior {
  BlockType<BlockGreenConcreteBehavior> GREEN_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockGreenConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_CONCRETE)
          .build();
}
