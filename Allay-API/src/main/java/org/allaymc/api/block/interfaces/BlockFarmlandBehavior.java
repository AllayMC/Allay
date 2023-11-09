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
public interface BlockFarmlandBehavior extends BlockBehavior {
  BlockType<BlockFarmlandBehavior> FARMLAND_TYPE = BlockTypeBuilder
          .builder(BlockFarmlandBehavior.class)
          .vanillaBlock(VanillaBlockId.FARMLAND)
          .setProperties(VanillaBlockPropertyTypes.MOISTURIZED_AMOUNT)
          .build();
}
