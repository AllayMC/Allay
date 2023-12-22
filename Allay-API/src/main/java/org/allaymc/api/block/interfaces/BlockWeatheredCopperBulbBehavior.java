package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWeatheredCopperBulbBehavior extends BlockBehavior {
  BlockType<BlockWeatheredCopperBulbBehavior> WEATHERED_COPPER_BULB_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCopperBulbBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_COPPER_BULB)
          .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
          .build();
}