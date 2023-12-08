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
public interface BlockWaxedCopperTrapdoorBehavior extends BlockBehavior {
  BlockType<BlockWaxedCopperTrapdoorBehavior> WAXED_COPPER_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCopperTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_COPPER_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .build();
}
