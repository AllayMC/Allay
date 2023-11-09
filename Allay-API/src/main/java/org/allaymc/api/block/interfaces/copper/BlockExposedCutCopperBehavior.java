package org.allaymc.api.block.interfaces.copper;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockExposedCutCopperBehavior extends BlockBehavior {
  BlockType<BlockExposedCutCopperBehavior> EXPOSED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockExposedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER)
          .build();
}
