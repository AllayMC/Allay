package org.allaymc.api.block.interfaces.copper;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWeatheredChiseledCopperBehavior extends BlockBehavior {
  BlockType<BlockWeatheredChiseledCopperBehavior> WEATHERED_CHISELED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredChiseledCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_CHISELED_COPPER)
          .build();
}
