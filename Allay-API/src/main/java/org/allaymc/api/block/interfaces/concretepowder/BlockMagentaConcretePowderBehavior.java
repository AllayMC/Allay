package org.allaymc.api.block.interfaces.concretepowder;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMagentaConcretePowderBehavior extends BlockBehavior {
  BlockType<BlockMagentaConcretePowderBehavior> MAGENTA_CONCRETE_POWDER_TYPE = BlockTypeBuilder
          .builder(BlockMagentaConcretePowderBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CONCRETE_POWDER)
          .build();
}
