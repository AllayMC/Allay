package org.allaymc.api.block.interfaces.glass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGlassBehavior extends BlockBehavior {
  BlockType<BlockGlassBehavior> GLASS_TYPE = BlockTypeBuilder
          .builder(BlockGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.GLASS)
          .build();
}
