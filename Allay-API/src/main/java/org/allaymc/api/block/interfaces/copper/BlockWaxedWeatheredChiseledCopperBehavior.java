package org.allaymc.api.block.interfaces.copper;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedWeatheredChiseledCopperBehavior extends BlockBehavior {
  BlockType<BlockWaxedWeatheredChiseledCopperBehavior> WAXED_WEATHERED_CHISELED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredChiseledCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CHISELED_COPPER)
          .build();
}
