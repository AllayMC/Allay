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
public interface BlockDoublePlantBehavior extends BlockBehavior {
  BlockType<BlockDoublePlantBehavior> DOUBLE_PLANT_TYPE = BlockTypeBuilder
          .builder(BlockDoublePlantBehavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_PLANT)
          .setProperties(VanillaBlockPropertyTypes.DOUBLE_PLANT_TYPE, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .build();
}
