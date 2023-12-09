package org.allaymc.api.block.interfaces.trapdoor;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedExposedCopperTrapdoorBehavior extends BlockBehavior {
  BlockType<BlockWaxedExposedCopperTrapdoorBehavior> WAXED_EXPOSED_COPPER_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCopperTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_COPPER_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .build();
}
