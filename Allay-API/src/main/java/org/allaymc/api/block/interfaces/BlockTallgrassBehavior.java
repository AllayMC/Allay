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
public interface BlockTallgrassBehavior extends BlockBehavior {
  BlockType<BlockTallgrassBehavior> TALLGRASS_TYPE = BlockTypeBuilder
          .builder(BlockTallgrassBehavior.class)
          .vanillaBlock(VanillaBlockId.TALLGRASS)
          .setProperties(VanillaBlockPropertyTypes.TALL_GRASS_TYPE)
          .build();
}
