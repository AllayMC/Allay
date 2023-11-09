package org.allaymc.api.block.interfaces.copper;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWeatheredCopperBehavior extends BlockBehavior {
  BlockType<BlockWeatheredCopperBehavior> WEATHERED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_COPPER)
          .build();
}
