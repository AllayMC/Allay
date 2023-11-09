package org.allaymc.api.block.interfaces.concretepowder;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlueConcretePowderBehavior extends BlockBehavior {
  BlockType<BlockBlueConcretePowderBehavior> BLUE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
          .builder(BlockBlueConcretePowderBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_CONCRETE_POWDER)
          .build();
}
