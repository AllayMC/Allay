package org.allaymc.api.block.interfaces.copper;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockExposedCopperBehavior extends BlockBehavior {
  BlockType<BlockExposedCopperBehavior> EXPOSED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockExposedCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_COPPER)
          .build();
}
