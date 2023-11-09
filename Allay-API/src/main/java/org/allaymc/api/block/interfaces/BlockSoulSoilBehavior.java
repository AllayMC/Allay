package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSoulSoilBehavior extends BlockBehavior {
  BlockType<BlockSoulSoilBehavior> SOUL_SOIL_TYPE = BlockTypeBuilder
          .builder(BlockSoulSoilBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_SOIL)
          .build();
}
