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
public interface BlockComposterBehavior extends BlockBehavior {
  BlockType<BlockComposterBehavior> COMPOSTER_TYPE = BlockTypeBuilder
          .builder(BlockComposterBehavior.class)
          .vanillaBlock(VanillaBlockId.COMPOSTER)
          .setProperties(VanillaBlockPropertyTypes.COMPOSTER_FILL_LEVEL)
          .build();
}
